package com.application.service;

import com.application.TemplateDto;
import com.application.mapper.TemplateMapper;
import com.application.model.Template;
import com.application.repository.TemplateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TemplatePersistenceService {

    private final TemplateRepository repository;
    private final TemplateMapper mapper;

    @Transactional
    public TemplateDto saveTemplate(TemplateDto dto) {
        Template template = mapper.templateDtoToTemplate(dto);
        return mapper.templateToTemplateDto(repository.save(template));
    }

    @Transactional(readOnly = true)
    public List<TemplateDto> getAllTemplates() {
        return repository.findAll()
                         .stream()
                         .map(mapper::templateToTemplateDto)
                         .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TemplateDto getTemplate(Long id) {
        return mapper.templateToTemplateDto(getTemplateFromRepository(id));
    }

    private Template getTemplateFromRepository(Long id) {
        return repository.findById(id)
                         .orElseThrow(() -> new RuntimeException("Template not found"));
    }


}
