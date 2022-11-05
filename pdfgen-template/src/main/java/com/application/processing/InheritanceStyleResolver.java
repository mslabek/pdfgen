package com.application.processing;

import com.application.TemplateDto;
import com.application.TemplateNodeDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InheritanceStyleResolver implements StyleResolver {


    public void resolve(TemplateDto template) {
        TemplateNodeDto root = template.getRoot();
        inheritStyles(root);
        resolveStylePriorityRecursively(root);
    }

    private void inheritStyles(TemplateNodeDto node) {
        node.getStyles()
            .keySet()
            .stream()
            .filter(this::isInheritedStyle)
            .forEach(s -> setStyleForAllDescendants(node, s, node.getStyles()
                                                                 .get(s)));
    }

    private void setStyleForAllDescendants(TemplateNodeDto node, String style, String value) {
        node.getChildren()
            .forEach(child -> {
                if (!child.getStyles().containsKey(style)) {
                    child.getStyles()
                         .put(style, value);
                    setStyleForAllDescendants(child, style, value);
                }
            });
    }

    private void resolveStylePriorityRecursively(TemplateNodeDto node) {
        List<String> stylesToInherit = getStylesToInherit(node);
        setStylesFromInheritedStyles(node, stylesToInherit);
        removeInheritedStyles(node, stylesToInherit);

        node.getChildren()
            .forEach(this::resolveStylePriorityRecursively);
    }

    private void removeInheritedStyles(TemplateNodeDto node, List<String> stylesToInherit) {
        stylesToInherit.forEach(s -> node.getStyles().remove(s));
    }

    private void setStylesFromInheritedStyles(TemplateNodeDto node, List<String> stylesToInherit) {
        stylesToInherit.stream()
                       .filter(s -> !node.getStyles()
                                         .containsKey(getStandardStyle(s)))
                       .forEach(s -> node.getStyles()
                                         .put(getStandardStyle(s), node.getStyles()
                                                                       .get(s)));
    }

    private List<String> getStylesToInherit(TemplateNodeDto node) {
        return node.getStyles()
                   .keySet()
                   .stream()
                   .filter(this::isInheritedStyle)
                   .collect(Collectors.toList());
    }

    private String getStandardStyle(String inheritedStyle) {
        if (!isInheritedStyle(inheritedStyle)) {
            throw new RuntimeException("This is not an inherited style");
        }
        return inheritedStyle.substring(2);
    }


    private boolean isInheritedStyle(String s) {
        return s.startsWith("i-");
    }

}
