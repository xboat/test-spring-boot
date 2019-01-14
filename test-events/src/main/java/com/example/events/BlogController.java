package com.example.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 * @author xboat date 2019-01-14
 */
@RestController
@RequestMapping("/api")
public class BlogController {

    @Autowired
    private IEventPublisher eventPublisher;
    private List<String> list = new LinkedList<>();
    @RequestMapping("/send")
    public String send(@RequestParam(value="name") String name) {
        BlogPost blogPost = new BlogPost();
        blogPost.setId(1);
        blogPost.setName(name);
        BaseEntityEvent entityEvent = new BaseEntityEvent<BlogPost>(blogPost);
        entityEvent.setActinType(ActionTypeEnum.Select);
        eventPublisher.publish(entityEvent);
        return name;
    }
}
