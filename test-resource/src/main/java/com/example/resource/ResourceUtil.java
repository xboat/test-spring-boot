package com.example.resource;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.*;


/**
 * @author xboat date 2019-08-07
 */
public class ResourceUtil {

    private static final Map<String, Properties> HASH_MAP = new HashMap<>();

    static {
        ResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        try {
            Resource[] classResources = patternResolver.getResources("classpath*:com/example/resource/*Service.class");
            for(Resource r : classResources){
                System.out.println("URL:" + r.getURL());
            }
            //获取资源
            Resource[] resources = patternResolver.getResources("classpath*:META-INF/com/example/resource/**");
            for (Resource resource : resources) {
                Properties props = new Properties();
                //加载资源
                props.load(resource.getInputStream());
                String fileName = resource.getFilename();
                if (HASH_MAP.containsKey(fileName)) {
                    Properties properties = HASH_MAP.get(fileName);
                    for (Map.Entry<Object, Object> entry: props.entrySet() ){
                        properties.setProperty((String)entry.getKey(),(String)entry.getValue());
                    }
                } else {
                    //装载资源
                    HASH_MAP.put(fileName, props);
                }
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
    }


    public static Set<String> getPropertyValueSet(String fileName) {
        Set<String> setStr = new HashSet<>();
        Properties property = getProperty(fileName);
        if (property != null) {
            Set<String> strings = property.stringPropertyNames();
            for (String key : strings) {
                setStr.add(property.get(key).toString());
            }
        }
        return setStr;
    }

    public static Set<String> getPropertyKeySet(String filName) {
        Properties property = getProperty(filName);
        return property != null ? property.stringPropertyNames() : new HashSet<>();
    }

    private static Properties getProperty(String filName) {
        return HASH_MAP.get(filName);
    }


    public static String getProperty(String fileName, String key) {
        Properties properties = getProperty(fileName);
        if (properties != null) {
            return properties.get(key) != null ? properties.get(key).toString() : null;
        }
        return null;
    }

    public static void setProperty(String fileName, String key, String value) {
        Properties properties = getProperty(fileName);
        if (properties != null) {
            if (properties.get(key) != null) {
                properties.setProperty(key, value);
            }
        }
    }
}
