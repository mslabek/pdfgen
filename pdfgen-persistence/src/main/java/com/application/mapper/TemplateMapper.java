package com.application.mapper;

import com.application.TemplateDto;
import com.application.model.Template;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses =
        TemplateNodeMapper.class)
public interface TemplateMapper {

    Template templateDtoToTemplate(TemplateDto source);

    TemplateDto templateToTemplateDto(Template source);

}
