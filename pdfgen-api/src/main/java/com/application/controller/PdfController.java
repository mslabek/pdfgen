package com.application.controller;

import com.application.TemplateDto;
import com.application.form.TemplateForm;
import com.application.form.ValuesForm;
import com.application.mapper.TemplateFormMapper;
import com.application.service.PdfGenerator;
import com.application.processing.TemplateProcessor;
import com.application.service.TemplatePersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
public class PdfController {

    private final PdfGenerator generator;
    private final TemplateProcessor templateProcessor;
    private final TemplatePersistenceService templatePersistenceService;
    private final TemplateFormMapper templateFormMapper;

    private static final String DEFAULT_FILE_NAME = "generatedFile.pdf";

    @PostMapping(value = "pdf/{templateId}")
    public ResponseEntity<Resource> generatePdfFromTemplate(@PathVariable Long templateId, @RequestBody ValuesForm body) {
        TemplateDto chosenTemplate = templatePersistenceService.getTemplate(templateId);
        templateProcessor.processTemplate(chosenTemplate, body.getValues());
        Resource file = generator.generate(chosenTemplate);
        return getPdfResponse(file, DEFAULT_FILE_NAME, MediaType.APPLICATION_PDF);
    }

    @PostMapping(value = "pdf/swagger/{templateId}")
    public ResponseEntity<Resource> generatePdfFromTemplateAsOctetStream(@PathVariable Long templateId, @RequestBody ValuesForm body) {
        TemplateDto chosenTemplate = templatePersistenceService.getTemplate(templateId);
        templateProcessor.processTemplate(chosenTemplate, body.getValues());
        Resource file = generator.generate(chosenTemplate);
        return getPdfResponse(file, DEFAULT_FILE_NAME, MediaType.APPLICATION_OCTET_STREAM);
    }

    @PostMapping("pdf")
    public ResponseEntity<Resource> generatePdfFromForm(@RequestBody @Valid TemplateForm form) {
        TemplateDto templateDto = templateFormMapper.templateFormToTemplateDto(form);
        Resource pdf = generator.generate(templateDto);
        return getPdfResponse(pdf, DEFAULT_FILE_NAME, MediaType.APPLICATION_PDF);
    }

    @PostMapping("pdf/swagger")
    public ResponseEntity<Resource> generatePdfFromFormAsOctetStream(@RequestBody @Valid TemplateForm form) {
        TemplateDto templateDto = templateFormMapper.templateFormToTemplateDto(form);
        Resource pdf = generator.generate(templateDto);
        return getPdfResponse(pdf, DEFAULT_FILE_NAME, MediaType.APPLICATION_OCTET_STREAM);
    }

    private ResponseEntity<Resource> getPdfResponse(Resource file, String fileName, MediaType mediaType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        headers.setContentDisposition(ContentDisposition.attachment()
                                                        .filename("generatedFile.pdf")
                                                        .build());
        return new ResponseEntity<>(file, headers, HttpStatus.OK);
    }

}
