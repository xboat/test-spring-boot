package com.example.caching;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xboat date 2019-01-03
 */
@RestController
@RequestMapping("/api")
public class CacheController {

    @Autowired
    private ICacheService cacheService;

    @RequestMapping("/cacheable")
    public void cacheable(@RequestParam(name="id") String id){
      String str = cacheService.setCache(id);
      System.out.println(str);
      String str2 = cacheService.getCache(id);
      System.out.println(str2);
    }

    @RequestMapping("/cachePut")
    public void cachePut(@RequestParam(name="id") String id){
        String str = cacheService.cachePut(id);
        System.out.println(str);
    }

    @RequestMapping("/cacheEvict")
    public void cacheEvict(@RequestParam(name="id") String id){
        String str = cacheService.cacheEvict(id);
        System.out.println(str);
    }


}
