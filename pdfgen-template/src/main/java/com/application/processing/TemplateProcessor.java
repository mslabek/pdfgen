package com.application.processing;

import com.application.TemplateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class TemplateProcessor {

    private final StyleResolver inheritanceStyleResolver;
    private final TextResolver textResolver;

    public void processTemplate(TemplateDto templateDto, Map<String, String> valuesMap) {
        inheritanceStyleResolver.resolve(templateDto);
        textResolver.resolve(templateDto, valuesMap);
    }


}
