package com.example.events;

/**
 * @author xboat date 2019-01-14
 */
public interface IEventPublisher {

  public <T>  void publish(T eventMessage);
}
