package com.example.events;

import java.util.List;

/**
 * @author xboat date 2019-01-14
 */
public interface ISubscriptionService {
    public <T> List<IConsumer<T>> getSubscriptions();
}
