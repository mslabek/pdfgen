package com.application.controller;

import com.application.TemplateDto;
import com.application.form.TemplateForm;
import com.application.form.ValuesForm;
import com.application.mapper.TemplateFormMapper;
import com.application.processing.TemplateProcessor;
import com.application.service.PdfGenerator;
import com.application.service.TemplatePersistenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    @Operation(summary = "Generate a PDF file from a persisted template and sent placeholders", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "JSON object containing values to replace the placeholders"))
    @Parameter(name = "templateId", description = "the id of the template to be used")
    @ApiResponse(responseCode = "200", description = "PDF file generated successfully")
    @ApiResponse(responseCode = "5xx", description = "Unexpected error", content = @Content)
    public ResponseEntity<Resource> generatePdfFromTemplate(@PathVariable Long templateId, @RequestBody ValuesForm body) {
        TemplateDto chosenTemplate = templatePersistenceService.getTemplate(templateId);
        templateProcessor.processTemplate(chosenTemplate, body.getValues());
        Resource file = generator.generate(chosenTemplate);
        return getPdfResponse(file, DEFAULT_FILE_NAME, MediaType.APPLICATION_PDF);
    }

    @PostMapping(value = "pdf/swagger/{templateId}")
    @Operation(summary = "Generate a PDF file from a persisted template and sent placeholders (compatible with Swagger)", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "JSON object containing values to replace the placeholders"))
    @Parameter(name = "templateId", description = "the id of the template to be used")
    @ApiResponse(responseCode = "200", description = "PDF file generated successfully")
    @ApiResponse(responseCode = "5xx", description = "Unexpected error", content = @Content)
    public ResponseEntity<Resource> generatePdfFromTemplateAsOctetStream(@PathVariable Long templateId, @RequestBody ValuesForm body) {
        TemplateDto chosenTemplate = templatePersistenceService.getTemplate(templateId);
        templateProcessor.processTemplate(chosenTemplate, body.getValues());
        Resource file = generator.generate(chosenTemplate);
        return getPdfResponse(file, DEFAULT_FILE_NAME, MediaType.APPLICATION_OCTET_STREAM);
    }

    @PostMapping("pdf")
    @Operation(summary = "Generate a PDF file from a template without placeholder values", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "JSON object containing template of the PDF file to be created"))
    @ApiResponse(responseCode = "200", description = "PDF file generated successfully")
    @ApiResponse(responseCode = "400", description = "Request validation error", content = @Content)
    @ApiResponse(responseCode = "5xx", description = "Unexpected error", content = @Content)
    public ResponseEntity<Resource> generatePdfFromForm(@RequestBody @Valid TemplateForm form) {
        TemplateDto templateDto = templateFormMapper.templateFormToTemplateDto(form);
        Resource pdf = generator.generate(templateDto);
        return getPdfResponse(pdf, DEFAULT_FILE_NAME, MediaType.APPLICATION_PDF);
    }

    @PostMapping("pdf/swagger")
    @Operation(summary = "Generate a PDF file from a template without placeholder values (compatible with Swagger)", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "JSON object containing template of the PDF file to be created"))
    @ApiResponse(responseCode = "200", description = "PDF file generated successfully")
    @ApiResponse(responseCode = "400", description = "Request validation error", content = @Content)
    @ApiResponse(responseCode = "5xx", description = "Unexpected error", content = @Content)
    public ResponseEntity<Resource> generatePdfFromFormAsOctetStream(@RequestBody @Valid TemplateForm form) {
        TemplateDto templateDto = templateFormMapper.templateFormToTemplateDto(form);
        Resource pdf = generator.generate(templateDto);
        return getPdfResponse(pdf, DEFAULT_FILE_NAME, MediaType.APPLICATION_OCTET_STREAM);
    }

    private ResponseEntity<Resource> getPdfResponse(Resource file, String fileName, MediaType mediaType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        headers.setContentDisposition(ContentDisposition.attachment().filename(fileName).build());
        return new ResponseEntity<>(file, headers, HttpStatus.OK);
    }

}
