package com.application.preprocessing;

import com.application.TemplateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemplatePreProcessor {

    private final PlaceholderResolver placeholderResolver;
    private final TypeResolver typeResolver;

    public void process(TemplateDto template) {
        placeholderResolver.updateTemplatePlaceholders(template);
        typeResolver.resolve(template);
    }


}
