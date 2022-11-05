package com.application.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TemplateNode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private Map<String, String> styles = new HashMap<>();

    private String text;

    private String type;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<TemplateNode> children = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private TemplateNode parent;

    public void addChild(TemplateNode childNode) {
        getChildren().add(childNode);
        childNode.setParent(this);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStyles(Map<String, String> styles) {
        this.styles = styles;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setParent(TemplateNode parent) {
        this.parent = parent;
    }
}
