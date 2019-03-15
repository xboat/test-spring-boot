package com.example.thymeleaf.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * @author xboat date 2019-03-14
 */


@Entity
@Table(name = "blog_post")
public class BlogPost extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String body;
    private String tags;
    @Column(name = "created_at")
    private Date createdAt;

    public BlogPost(){

    }

    public BlogPost(Long id, String title, String body, String tags, Date createdAt) {
        this.id=id;
        this.title=title;
        this.body=body;
        this.tags=tags;
        this.createdAt=createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "BlogPost{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", tags='" + tags + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }

}
