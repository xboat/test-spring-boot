package com.example.events;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author xboat date 2019-01-14
 */
@Component
public class SubscriptionService implements ISubscriptionService {
    @Override
    public <T> List<IConsumer<T>> getSubscriptions() {
        List<IConsumer<T>> list = new ArrayList<>();
        Class s= IConsumer.class;
        Map<String, IConsumer<T>> map =   SpringContextUtils.getBeansOfType(s);
        for (IConsumer<T> consumer: map.values()) {
           list.add(consumer);
        }
        return list;
    }
}
