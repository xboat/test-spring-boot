package com.example.myBatis.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author xboat date 2018-12-18
 */
@Data
@Table(name = "blog_post")
public class BlogPost implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;


    @Column(name = "creator")
    private String creator;

    @Column(name = "created_at")
    private Date createdAt;

}
