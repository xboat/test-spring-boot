package com.example.events;

/**
 * @author xboat date 2019-01-14
 */
public interface IConsumer<T>  {
    void handleEvent(T eventMessage);
}
