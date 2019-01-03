package com.example.caching;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

/**
 * @author xboat date 2019-01-03
 */
@Component
public class CacheService implements ICacheService {

    private int i;

    @Override
    @Cacheable(cacheNames = "cache1", key = "#id", condition = "#id != '88'")
    public String setCache(String id) {
        i += 1;
        return i + "-->setCache";
    }

    @Override
    @Cacheable("cache1")
    public String getCache(String id) {
        return i + "-->getCache";
    }

    @Override
    @CachePut("cache1")
    public String cachePut(String id) {
        return i + "-->cachePut";
    }

    @Override
    @CacheEvict(value = "cache2", key = "#id")
    public String cacheEvict(String id) {
        return i + "-->removeCache";
    }

    @Caching(cacheable = @Cacheable("test_key"), evict = { @CacheEvict("test_key"),
            @CacheEvict(value = "cacheEvict_key", allEntries = true) })
    public String caching(String id) {
        return i + "-->caching";
    }
}
