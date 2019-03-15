package com.example.thymeleaf.service.impl;

import com.example.thymeleaf.domain.BlogPost;
import com.example.thymeleaf.repository.BlogPostRepository;
import com.example.thymeleaf.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("blogPostService")
public class BlogPostServiceImpl implements BlogPostService {

    private BlogPostRepository blogPostRepository;

    @Autowired
    public BlogPostServiceImpl(BlogPostRepository blogPostRepository){
        this.blogPostRepository =blogPostRepository;
    }

    @Override
    public List<BlogPost> getBlogPosts(){
       return blogPostRepository.findAll();
    }

    @Override
    public BlogPost getBlogPost(long id){
        return blogPostRepository.getOne(id);
    }
}
