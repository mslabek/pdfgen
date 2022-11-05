package com.application.form;

import com.application.TemplateNodeDto;
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
    private TemplateNodeDto root;

}
