package com.application.processing;

import com.application.TemplateDto;
import com.application.TemplateNodeDto;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SimpleTextResolver implements TextResolver {

    public void resolve(TemplateDto template, Map<String, String> values) {
        resolveRootNode(template.getRoot(),  values);
    }

    private void resolveRootNode(TemplateNodeDto root, Map<String, String> valuesMap) {
        resolveNode(root, valuesMap);
        for (TemplateNodeDto child : root.getChildren()) {
            resolveRootNode(child, valuesMap);
        }
    }

    private void resolveNode(TemplateNodeDto node, Map<String, String> valuesMap) {
        String resolvedText = resolveText(node.getText(), valuesMap);
        node.setText(resolvedText);
    }

    private String resolveText(String template, Map<String, String> valuesMap) {
        StringSubstitutor sub = new StringSubstitutor(valuesMap);
        return sub.replace(template);
    }

}
