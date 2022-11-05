package com.application.model;

import com.application.service.PdfStyleStrategy;
import com.lowagie.text.*;
import com.lowagie.text.pdf.draw.LineSeparator;

public enum NodeType {

    BLOCK {
        @Override
        public Element generate(PdfNode pdfNode, PdfStyleStrategy styleStrategy) {
            Paragraph element = new Paragraph();
            styleStrategy.applyDefaultStyles(element);
            styleStrategy.applyStyles(element, pdfNode.getStyles());
            element.add(pdfNode.getText());
            return element;
        }
    },
    LINE_BREAK {
        @Override
        public Element generate(PdfNode pdfNode, PdfStyleStrategy styleStrategy) {
            Paragraph paragraph = new Paragraph();
            styleStrategy.applyDefaultStyles(paragraph);
            styleStrategy.applyStyles(paragraph, pdfNode.getStyles());
            LineSeparator separator = new LineSeparator();
            paragraph.add(separator);
            return paragraph;
        }
    };

    public abstract Element generate(PdfNode pdfNode, PdfStyleStrategy styleStrategy);

}
