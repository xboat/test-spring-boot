package com.example.thymeleaf.controller;

import com.example.thymeleaf.domain.BlogPost;
import com.example.thymeleaf.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author xboat date 2019-03-14
 */
@Controller
@RequestMapping("/")
public class BlogPostController extends BaseController{

    private BlogPostService blogPostService;

    @Autowired
    public BlogPostController(BlogPostService blogPostService){
        this.blogPostService = blogPostService;
    }

    @RequestMapping(value="/")
    public String index(Model model) {
        model.addAttribute("title", "博客列表");
        return "index";
    }

    @RequestMapping(value="/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") Integer page) {
        model.addAttribute("title", "博客列表");
//        List<BlogPost> blogPosts= new LinkedList<>();
//        for (int i = 0; i <10; i++) {
//            blogPosts.add(new BlogPost(Long.valueOf(i),"标题"+i,"内容"+i,"标签"+i,new Date()));
//        }
        List<BlogPost> blogPosts = blogPostService.getBlogPosts();
        model.addAttribute("blogPosts", blogPosts);
        return "list";
    }

    @RequestMapping(value="/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        BlogPost blogPost = blogPostService.getBlogPost(id);
        model.addAttribute("blogPost", blogPost);
        logger.info("测试下");
        return "detail";
    }
}
