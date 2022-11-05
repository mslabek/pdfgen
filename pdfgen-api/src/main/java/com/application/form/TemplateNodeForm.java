package com.application.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TemplateNodeForm {

    private Map<String, String> styles = new HashMap<>();

    private String text;

    private String type;

    private List<TemplateNodeForm> children = new ArrayList<>();

}
