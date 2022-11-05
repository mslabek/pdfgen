package com.application.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PdfNode {

    private String text;
    private NodeType type;
    private List<StyleConfig> styles;
    private List<PdfNode> children;

}
