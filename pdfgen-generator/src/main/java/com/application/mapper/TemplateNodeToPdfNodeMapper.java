package com.application.mapper;

import com.application.model.NodeType;
import com.application.model.PdfNode;
import com.application.TemplateNodeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TemplateNodeToPdfNodeMapper {

    private final StyleMapper styleMapper;

    public PdfNode map(TemplateNodeDto source) {
        PdfNode target = new PdfNode();
        target.setText(source.getText());
        target.setType(NodeType.valueOf(source.getType().toUpperCase()));
        List<PdfNode> children = new ArrayList<>();
        for (TemplateNodeDto child : source.getChildren()) {
            children.add(map(child));
        }
        target.setChildren(children);
        target.setStyles(styleMapper.map(source.getStyles()));
        return target;
    }

}
