package com.example.events;

import org.springframework.stereotype.Component;

/**
 * @author xboat date 2019-01-14
 */
@Component
public class Consumer implements IConsumer<BaseEntityEvent<BlogPost>>{

   @Override
    public void handleEvent(BaseEntityEvent<BlogPost> eventMessage) {
       System.out.println("收到消息----->"+eventMessage.getEntity().getName());
       System.out.println("动作类型-->"+eventMessage.getActinType());
    }
}
