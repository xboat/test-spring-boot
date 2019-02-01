
package com.example.shardingjdbc.config;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;

public class DataSourceUtil {
    
    private static final String HOST = "localhost";
    
    private static final int PORT = 3306;
    
    private static final String USER_NAME = "root";
    
    private static final String PASSWORD = "qwe123";
    
    public static DataSource createDataSource(final String dataSourceName) {
        BasicDataSource result = new BasicDataSource();
        result.setDriverClassName(com.mysql.cj.jdbc.Driver.class.getName());
        result.setUrl(String.format("jdbc:mysql://%s:%s/%s?useJDBCCompliantTimezoneShift = true&useLegacyDatetimeCode=false&serverTimezone=Asia/Shanghai&useSSL=false", HOST, PORT, dataSourceName));
        result.setUsername(USER_NAME);
        result.setPassword(PASSWORD);
        return result;
    }
}
