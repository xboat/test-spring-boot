package com.example.myBatis.service;

import com.example.myBatis.mapper.BlogPostMapper;
import com.example.myBatis.model.BlogPost;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.Date;
import java.util.List;

/**
 * @author xboat date 2018-12-22
 */
@SuppressWarnings("ALL")
@Service
public class BlogPostService {

    @Autowired
    private BlogPostMapper blogPostMapper;

    public Boolean insert(BlogPost model){
       return blogPostMapper.insert(model)>0;
    }

    public BlogPost getModel(Integer id){
        BlogPost model= new BlogPost();
        model.setId(id);
        return blogPostMapper.selectOne(model) ;
    }


    public PageInfo<BlogPost> getPagedList(Integer pageIndex, Integer pageSize, String title,Date startDate, Date endDate){
        Example example = new Example(BlogPost.class);
        example.orderBy("createdAt").desc();
        Example.Criteria criteria = example.createCriteria();
        if(StringUtil.isNotEmpty(title)) {
            criteria.andEqualTo("title", title);
        }
        if(startDate!= null && endDate != null) {
            criteria.andBetween("createdAt", startDate, endDate);
        }
        PageHelper.startPage(pageIndex, pageSize);
        List<BlogPost> blogPosts = blogPostMapper.selectByExample(example);
        PageInfo<BlogPost> pageInfo = new PageInfo<>(blogPosts);
        return pageInfo;
    }

    public Integer updateById(BlogPost blogPost){
       return blogPostMapper.updateById(blogPost);
    }

    public Integer getTotal(String title){
        return blogPostMapper.getTotal(title);
    }
}
