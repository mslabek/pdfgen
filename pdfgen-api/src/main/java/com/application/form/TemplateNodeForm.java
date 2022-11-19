package com.application.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TemplateNodeForm {

    @Schema(description = "Styles to be applied to the node", example = "{}")
    private Map<String, String> styles = new HashMap<>();

    @Schema(description = "Text value of the node", example = "Hello world ${examplePlaceholder}")
    private String text;

    @Schema(description = "Type of node", example = "BLOCK")
    private String type;

    @Schema(description = "Children of the node", example = "[]")
    private List<TemplateNodeForm> children = new ArrayList<>();

}
