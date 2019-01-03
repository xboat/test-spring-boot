package com.example.caching;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author xboat date 2019-01-03
 */

@Configuration
public class CaffeineCacheConfiguration {

    @Bean
    public CacheManager caffeineCacheManager() {
        CaffeineCache cache1 = buildCaffeineCache("cache1", new HashMap<String, Object>() {
            {
                put("timeout", Long.valueOf(5));
            }
        });
        CaffeineCache cache2 = buildCaffeineCache("cache2", new HashMap<String, Object>() {
            {
                put("timeout", Long.valueOf(10));
            }
        });
        List<Cache> cacheList = new ArrayList<>();
        cacheList.add(cache1);
        cacheList.add(cache2);
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(cacheList);
        return cacheManager;
    }

    private CaffeineCache buildCaffeineCache(String name, Map<String, Object> spec) {
        Caffeine<Object, Object> caffeineBuilder = Caffeine.newBuilder()
                .expireAfterWrite((Long) spec.get("timeout"), TimeUnit.SECONDS)
                .maximumSize(100);
        return new CaffeineCache(name, caffeineBuilder.build());
    }
}
