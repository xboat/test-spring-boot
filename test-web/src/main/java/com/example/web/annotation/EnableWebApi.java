package com.example.web.annotation;

import com.example.web.configuration.EnableWebApiImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author xboat date 2019-01-02
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({EnableWebApiImportSelector.class})
public @interface EnableWebApi {
}
