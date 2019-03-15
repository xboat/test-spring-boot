package com.example.thymeleaf.service;

import com.example.thymeleaf.domain.BlogPost;

import java.util.List;

public interface BlogPostService {
    List<BlogPost> getBlogPosts();
    BlogPost getBlogPost(long id);
}
