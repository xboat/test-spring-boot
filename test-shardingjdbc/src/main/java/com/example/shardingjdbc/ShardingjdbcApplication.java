package com.example.shardingjdbc;

import com.example.shardingjdbc.config.DataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;

/**
 * @author xboat date 2019-01-19
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@EnableTransactionManagement(proxyTargetClass = true)
public class ShardingjdbcApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(ShardingjdbcApplication.class, args);
        System.out.println("<---start shardingjdbc---->");
    }
}
