package com.application.controller;

import com.application.TemplateDto;
import com.application.form.TemplateForm;
import com.application.mapper.TemplateFormMapper;
import com.application.preprocessing.TemplatePreProcessor;
import com.application.service.TemplatePersistenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TemplateController {

    private final TemplatePersistenceService templatePersistenceService;
    private final TemplatePreProcessor templatePreProcessor;
    private final TemplateFormMapper templateFormMapper;

    @GetMapping("/template")
    @Operation(summary = "Retrieves all templates")
    @ApiResponse(responseCode = "200", description = "Templates retrieved successfully")
    @ApiResponse(responseCode = "5xx", description = "Unexpected error", content = @Content)
    public List<TemplateDto> getAllTemplates() {
        return templatePersistenceService.getAllTemplates();
    }

    @PostMapping("/template")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creates a new template", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "JSON object containing data of the template to be created"))
    @ApiResponse(responseCode = "201", description = "Template created successfully")
    @ApiResponse(responseCode = "400", description = "Request validation error", content = @Content)
    @ApiResponse(responseCode = "5xx", description = "Unexpected error", content = @Content)
    public TemplateDto createTemplate(@RequestBody @Valid TemplateForm form) {
        TemplateDto template = templateFormMapper.templateFormToTemplateDto(form);
        templatePreProcessor.process(template);
        return templatePersistenceService.saveTemplate(template);
    }


}
