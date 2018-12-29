package com.example.myBatis.service;

import com.example.myBatis.MyBatisApplication;
import com.example.myBatis.model.BlogPost;
import com.github.pagehelper.PageInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author xboat date 2018-12-22
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(MyBatisApplication.class)
public class BlogPostServiceTest {

    @Autowired
    private BlogPostService blogPostService;

    @Test
    public void insert() {
        BlogPost model = new BlogPost();
        model.setTitle("第0篇文章");
        model.setContent("测试");
        model.setCreator("张三");
        model.setCreatedAt(new Date());
        Boolean result = blogPostService.insert(model);
        System.out.println(result);
    }

    @Test
    public void getModel() {
        BlogPost project = blogPostService.getModel(1);
        System.out.println(project.getTitle());
    }

    @Test
    public void getPagedList() {
        PageInfo<BlogPost> pageInfo = blogPostService.getPagedList(1,10,null,null,null);
        System.out.println(pageInfo.getTotal());
    }

    @Test
    public void updateById() {
        BlogPost model = new BlogPost();
        model.setId(6);
        model.setTitle("第二9999篇文章");
        model.setContent("测试");
        model.setCreator("张三");
        model.setCreatedAt(new Date());
       Integer result = blogPostService.updateById(model);
       System.out.println(result);
       Assert.assertNotNull(result);
    }
    @Test
    public void getTotal(){
        BlogPost model = new BlogPost();
        model.setTitle("第0篇文章");
        Integer result = blogPostService.getTotal(model.getTitle());
        System.out.println(result);
        Assert.assertNotNull(result);
    }

}
