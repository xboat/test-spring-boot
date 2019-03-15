package com.example.thymeleaf.repository;

import com.example.thymeleaf.domain.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xboat date 2019-03-14
 */

//@Repository("blogPostRepository")
public interface BlogPostRepository  extends JpaRepository<BlogPost,Long> {

}
