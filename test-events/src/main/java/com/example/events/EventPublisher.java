package com.example.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xboat date 2019-01-14
 */
@Component
public class EventPublisher  implements IEventPublisher {

    @Autowired
    private ISubscriptionService _subscriptionService;

    public EventPublisher(ISubscriptionService subscriptionService) {
        _subscriptionService = subscriptionService;
    }

    protected <T> void publishToConsumer(IConsumer<T> x, T eventMessage)
    {
        try
        {
            x.handleEvent(eventMessage);
        }
        catch (Exception e)
        {
           System.out.println("publishToConsumer error--->"+e.getMessage());
        }
    }

    @Override
    public <T> void publish(T eventMessage) {
        List<IConsumer<T>> subscribers =_subscriptionService.getSubscriptions();
        subscribers.forEach(x-> publishToConsumer(x, eventMessage));
    }
}
