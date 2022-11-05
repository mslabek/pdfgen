package com.application.mapper;

import com.application.TemplateNodeDto;
import com.application.form.TemplateNodeForm;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, collectionMappingStrategy =
        CollectionMappingStrategy.ADDER_PREFERRED)
public interface TemplateNodeFormMapper {

    TemplateNodeDto templateFormToTemplateDto(TemplateNodeForm source);

}
