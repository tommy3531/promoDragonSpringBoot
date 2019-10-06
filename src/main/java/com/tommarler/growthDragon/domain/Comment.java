package com.tommarler.growthDragon.domain;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Optional;

@Document(collection = "comment")
public class Comment {

    public String id;
    public Date createdDate;
    @DBRef
    public User user;
    public String content;
    public Post post;

    public Comment(String id, Date createdDate, User user, String content, Post post) {
        this.id = id;
        this.createdDate = createdDate;
        this.user = user;
        this.content = content;
        this.post = post;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Comment() {}
}