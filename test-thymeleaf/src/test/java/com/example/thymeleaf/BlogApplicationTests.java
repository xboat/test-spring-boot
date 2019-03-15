//package com.example.thymeleaf;
//
//import com.example.thymeleaf.domain.BlogPost;
//import com.example.thymeleaf.repository.BlogPostRepository;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = ThymeleafApplication.class )
//public class BlogApplicationTests {
//
//	@Autowired
//	private BlogPostRepository repository;
//
//	@Test
//	public void getOne(){
//		BlogPost blogPost = repository.getOne(1l);
//		System.out.println(blogPost.getTitle());
//		Assert.assertNotNull(blogPost);
//		Assert.assertTrue(1l==blogPost.getId());
//	}
//
//}
