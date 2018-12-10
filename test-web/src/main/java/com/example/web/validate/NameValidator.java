package com.example.web.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author xboat date 2018-12-10
 */
public class NameValidator implements ConstraintValidator<NameValid, String> {

    @Override
    public void initialize(NameValid constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(!"test".equals(value)){
            String msg = context.getDefaultConstraintMessageTemplate();
            System.out.println("message--------->" + msg);
            return false;
        }
        return true;
    }

}
