package com.example.starter;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
/**
 * @author xboat date 2019-03-05
 */
@Data
@ConfigurationProperties("dubbo")
public class DubboProperties {
    private String username;
    private String password;
}