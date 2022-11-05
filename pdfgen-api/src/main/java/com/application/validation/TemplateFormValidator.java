package com.application.validation;

import com.application.TemplateDto;
import com.application.processing.TemplateProcessor;
import com.application.form.TemplateForm;
import com.application.mapper.TemplateFormMapper;
import com.application.service.PdfGenerator;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashMap;

@RequiredArgsConstructor
public class TemplateFormValidator implements ConstraintValidator<ValidTemplateForm, TemplateForm> {

    private final PdfGenerator generator;
    private final TemplateFormMapper mapper;
    private final TemplateProcessor resolverService;

    @Override
    public boolean isValid(TemplateForm value, ConstraintValidatorContext context) {
        try {
            TemplateDto template = mapper.templateFormToTemplateDto(value);
            resolverService.processTemplate(template, new HashMap<>());
            generator.generate(template);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
