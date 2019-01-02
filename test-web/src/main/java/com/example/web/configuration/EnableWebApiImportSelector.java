package com.example.web.configuration;

import com.example.web.annotation.EnableWebApi;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author xboat date 2019-01-02
 */
public class EnableWebApiImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(annotationMetadata.getAnnotationAttributes(EnableWebApi.class.getName()));
        return new String[]{Swagger2Config.class.getName(),WebMvcConfiguration.class.getName()};
    }
}
