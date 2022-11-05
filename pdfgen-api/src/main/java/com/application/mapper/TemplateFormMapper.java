package com.application.mapper;

import com.application.TemplateDto;
import com.application.form.TemplateForm;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, collectionMappingStrategy =
        CollectionMappingStrategy.ADDER_PREFERRED, uses = TemplateNodeFormMapper.class)
public interface TemplateFormMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "placeholders", ignore = true)
    TemplateDto templateFormToTemplateDto(TemplateForm source);

}
