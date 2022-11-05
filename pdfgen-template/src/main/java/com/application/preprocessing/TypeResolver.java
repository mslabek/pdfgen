package com.application.preprocessing;

import com.application.TemplateDto;
import com.application.TemplateNodeDto;
import org.springframework.stereotype.Service;

@Service
public class TypeResolver {

    private static final String DEFAULT_TYPE = "BLOCK";

    public void resolve(TemplateDto template) {
        resolveRecursively(template.getRoot());
    }

    public void resolveRecursively(TemplateNodeDto node) {
        if (node.getType() == null) {
            setDefaultType(node);
        } else {
            capitalizeType(node);
        }
        node.getChildren().forEach(this::resolveRecursively);
    }

    public void capitalizeType(TemplateNodeDto node) {
        node.setType(node.getType().toUpperCase());
    }

    public void setDefaultType(TemplateNodeDto node) {
        node.setType(DEFAULT_TYPE);
    }

}
