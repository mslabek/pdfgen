package com.application.mapper;

import com.application.TemplateNodeDto;
import com.application.model.TemplateNode;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, collectionMappingStrategy =
        CollectionMappingStrategy.ADDER_PREFERRED)
public interface TemplateNodeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "children", source = "children")
    TemplateNode nodeDtoToNode(TemplateNodeDto source);

    TemplateNodeDto nodeToNodeDto(TemplateNode source);

}
