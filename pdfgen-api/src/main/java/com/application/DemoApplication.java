package com.application;

import com.application.preprocessing.TemplatePreProcessor;
import com.application.service.TemplatePersistenceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
@AllArgsConstructor
public class DemoApplication implements CommandLineRunner {

    private final TemplatePersistenceService templatePersistenceService;
    private final TemplatePreProcessor templatePreProcessor;

    @Override
    public void run(String... args) throws Exception {
        addTemplateFromFile(new ClassPathResource("umowa-najmu.json").getFile());
        addTemplateFromFile(new ClassPathResource("hello-world-inheritance.json").getFile());
    }

    public void addTemplateFromFile(File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        TemplateDto template = objectMapper.readValue(file, TemplateDto.class);
        templatePreProcessor.process(template);
        templatePersistenceService.saveTemplate(template);
    }

}
