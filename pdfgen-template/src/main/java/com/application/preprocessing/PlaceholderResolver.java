package com.application.preprocessing;

import com.application.TemplateDto;
import com.application.TemplateNodeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class PlaceholderResolver {

    private final Pattern templatePattern;

    public void updateTemplatePlaceholders(TemplateDto template) {
        List<String> textContent = getAllTextContent(template);
        List<String> placeholders = extractPlaceholders(textContent);
        template.setPlaceholders(placeholders);
    }

    private List<String> getAllTextContent(TemplateDto template) {
        List<String> textContent = new ArrayList<>();
        getAllTextContentRecursively(template.getRoot(), textContent);
        return textContent;
    }

    private void getAllTextContentRecursively(TemplateNodeDto node, List<String> textContent) {
        if (node.getText() != null) {
            textContent.add(node.getText());
        }
        node.getChildren().forEach(child -> getAllTextContentRecursively(child, textContent));
    }

    private List<String> extractPlaceholders(List<String> textContent) {
        List<String> placeholders = new ArrayList<>();
        textContent.forEach(text -> {
            Matcher matcher = templatePattern.matcher(text);
            while (matcher.find()) {
                placeholders.add(matcher.group());
            }
        });
        return placeholders;
    }

}
