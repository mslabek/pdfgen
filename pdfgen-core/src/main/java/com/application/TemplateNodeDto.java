package com.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TemplateNodeDto {

    private Map<String, String> styles = new HashMap<>();

    private String text;

    private String type;

    private List<TemplateNodeDto> children = new ArrayList<>();

}
