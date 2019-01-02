package com.example.web.configuration;

import com.example.web.filter.CORSFilter;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author xboat date 2018-12-13
 */

@Data
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    private String name;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        System.out.println("addResourceHandlers--->"+this.name);

//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
//    @Bean
//    public CORSFilter corsFilter(){
//        return new CORSFilter();
//    }
}
