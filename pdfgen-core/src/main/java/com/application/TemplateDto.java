package com.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TemplateDto {

    private Long id;

    private String name;

    private List<String> placeholders;

    private TemplateNodeDto root;

}
