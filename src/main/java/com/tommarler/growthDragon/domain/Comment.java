package com.tommarler.growthDragon.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Optional;

@Document(collection = "comment")
public class Comment {

    @Id
    public String id;
    public Optional<Post> post;
    public Date createdDate;
    public User user;
    public String body;

    public Comment(Optional<Post> post, Date createdDate, User user, String body) {
        this.post = post;
        this.createdDate = createdDate;
        this.user = user;
        this.body = body;
    }

    public Comment() {}

    public Optional<Post> getPost() {
        return post;
    }

    public void setPost(Optional<Post> post) {
        this.post = post;
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

    public String getBody() {
        return body;
    }

    public void setBody(String comment) {
        this.body = body;
    }
}