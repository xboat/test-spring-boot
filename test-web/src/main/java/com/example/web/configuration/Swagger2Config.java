package com.example.web.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket controllerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("项目名称：swagger  接口")
                        .description("描述：项目描述")
                        .contact(new Contact("test", null, null))
                        .version("版本号：1.0.0")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.web"))
                .paths(PathSelectors.any())
                .build();
    }
}
