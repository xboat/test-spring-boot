package com.example.web.configuration;

import com.example.web.registrar.AbstractBeanRegistrar;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author xboat date 2018-12-10
 */
@EnableSwagger2
public class Swagger2Config extends AbstractBeanRegistrar {
    @Bean
    public Docket controllerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title(environment.getProperty("Swagger2Config.title"))
                        .description(environment.getProperty("Swagger2Config.description"))
                        .contact(new Contact(environment.getProperty("Swagger2Config.name"), null, null))
                        .version(environment.getProperty("Swagger2Config.version"))
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.web"))
                .paths(PathSelectors.any())
                .build();
    }

    @Override
    public void registerBeans() {

    }
}
