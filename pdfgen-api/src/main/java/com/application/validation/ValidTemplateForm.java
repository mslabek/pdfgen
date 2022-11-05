package com.application.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TemplateFormValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTemplateForm {

    String message() default "{com.application.validation.ValidTemplateForm.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
