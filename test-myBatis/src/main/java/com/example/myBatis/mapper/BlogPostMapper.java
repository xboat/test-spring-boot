package com.example.myBatis.mapper;

import com.example.myBatis.model.BlogPost;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;


/**
 * @author xboat date 2018-12-19
 */

public interface BlogPostMapper extends Mapper<BlogPost> {

    Integer updateById(BlogPost blogPost);

    Integer getTotal(@Param("title") String title);
}
