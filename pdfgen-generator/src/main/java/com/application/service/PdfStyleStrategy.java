package com.application.service;

import com.application.model.StyleConfig;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PdfStyleStrategy {

    private final BaseFont defaultBaseFont;

    public void applyStyles(Paragraph paragraph, List<StyleConfig> styles) {
        styles.forEach(style -> applyStyle(paragraph, style));
    }

    public void applyDefaultStyles(Paragraph paragraph) {
        paragraph.setFont(new Font(defaultBaseFont, 13));
    }

    public void applyStyle(Paragraph paragraph, StyleConfig style) {
        style.getStyle().applyStyle(paragraph, style.getValue());
    }

}
