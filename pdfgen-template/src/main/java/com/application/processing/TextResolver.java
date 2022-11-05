package com.application.processing;

import com.application.TemplateDto;

import java.util.Map;

public interface TextResolver {

    void resolve(TemplateDto template, Map<String, String> values);

}
