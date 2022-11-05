package com.application.service;

import com.application.model.PdfNode;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PdfGeneratorService {

    private final PdfStyleStrategy styleStrategy;

    public Resource generate(PdfNode root) {
        Document doc = new Document(PageSize.A4, 50,50,50,50);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(doc, byteArrayOutputStream);
        doc.open();
        generateElementsWithChildren(root).forEach(doc::add);
        doc.close();
        return new ByteArrayResource(byteArrayOutputStream.toByteArray());
    }

    public List<Element> generateElementsWithChildren(PdfNode pdfNode) {
        List<Element> elements = new ArrayList<>();
        elements.add(generateElement(pdfNode));
        for (PdfNode child : pdfNode.getChildren()) {
            elements.addAll(generateElementsWithChildren(child));
        }
        return elements;
    }

    public Element generateElement(PdfNode pdfNode) {
        return pdfNode.getType()
                      .generate(pdfNode, styleStrategy);
    }


}
