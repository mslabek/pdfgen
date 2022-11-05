package com.application.service;

import com.application.TemplateDto;
import com.application.mapper.TemplateNodeToPdfNodeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PdfGenerator {

    private final PdfGeneratorService pdfGeneratorService;
    private final TemplateNodeToPdfNodeMapper mapper;

    public Resource generate(TemplateDto template) {
        return pdfGeneratorService.generate(mapper.map(template.getRoot()));
    }

}
