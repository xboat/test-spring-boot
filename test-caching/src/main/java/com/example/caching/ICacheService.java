package com.example.caching;

/**
 * @author xboat date 2019-01-03
 */
public interface ICacheService {
    String setCache(String id);

    String getCache(String id);

    String cachePut(String id);

    String cacheEvict(String id);

}
