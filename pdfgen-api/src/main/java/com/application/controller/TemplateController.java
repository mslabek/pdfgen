package com.application.controller;

import com.application.TemplateDto;
import com.application.form.TemplateForm;
import com.application.mapper.TemplateFormMapper;
import com.application.preprocessing.TemplatePreProcessor;
import com.application.service.TemplatePersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TemplateController {

    private final TemplatePersistenceService templatePersistenceService;
    private final TemplatePreProcessor templatePreProcessor;
    private final TemplateFormMapper templateFormMapper;

    @GetMapping("/template")
    public List<TemplateDto> getAllTemplates() {
        return templatePersistenceService.getAllTemplates();
    }

    @PostMapping("/template")
    public TemplateDto createTemplate(@RequestBody @Valid TemplateForm form) {
        TemplateDto template = templateFormMapper.templateFormToTemplateDto(form);
        templatePreProcessor.process(template);
        return templatePersistenceService.saveTemplate(template);
    }



}
