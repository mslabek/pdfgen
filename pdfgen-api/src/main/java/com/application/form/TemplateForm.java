package com.application.form;

import com.application.validation.ValidTemplateForm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ValidTemplateForm
public class TemplateForm {

    private String name;

    private TemplateNodeForm root;

}
