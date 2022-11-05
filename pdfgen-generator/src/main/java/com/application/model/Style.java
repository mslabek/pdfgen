package com.application.model;

import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;

public enum Style {

    ALIGNMENT {
        @Override
        public void applyStyle(Paragraph paragraph, String value) {
            switch (value) {
                case "left":
                    paragraph.setAlignment(Element.ALIGN_LEFT);
                    break;
                case "center":
                    paragraph.setAlignment(Element.ALIGN_CENTER);
                    break;
                case "right":
                    paragraph.setAlignment(Element.ALIGN_RIGHT);
                    break;
                case "justify":
                    paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
                    break;
                default:
                    throw new RuntimeException("Illegal alignment style value.");
            }
        }
    },
    FONT_SIZE {
        @Override
        public void applyStyle(Paragraph paragraph, String value) {
            paragraph.getFont().setSize(Float.parseFloat(value));
        }
    },
    SPACING_AFTER {
        @Override
        public void applyStyle(Paragraph paragraph, String value) {
            paragraph.setSpacingAfter(Float.parseFloat(value));
        }
    },
    SPACING_BEFORE {
        @Override
        public void applyStyle(Paragraph paragraph, String value) {
            paragraph.setSpacingBefore(Float.parseFloat(value));
        }
    };

    public abstract void applyStyle(Paragraph paragraph, String value);
}
