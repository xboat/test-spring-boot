package com.example.web.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author xboat date 2018-12-10
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {NameValidator.class})
public @interface NameValid {
    String message() default "参数不合法";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
