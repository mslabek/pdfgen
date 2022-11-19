package com.application.form;

import com.application.validation.ValidTemplateForm;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ValidTemplateForm
public class TemplateForm {

    @Schema(description = "Template name", example = "My contract")
    private String name;

    private TemplateNodeForm root;

}
