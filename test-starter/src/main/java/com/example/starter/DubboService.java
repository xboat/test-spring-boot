package com.example.starter;

import java.util.HashMap;
/**
 * @author xboat date 2019-03-05
 */
public class DubboService {
    private HashMap<String, Object> map = new HashMap<String, Object>();
    public DubboService(DubboProperties properties) {
        super();
        System.out.println(String.format("dubbo---> name:%s password:%s",properties.getUsername(),properties.getPassword()));
    }

    public void put(String key, Object val) {
        map.put(key, val);
    }

    public Object  get(String key) {
        return map.get(key);
    }
}