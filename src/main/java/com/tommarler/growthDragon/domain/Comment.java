package com.tommarler.growthDragon.domain;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Comment {

    @Id
    public String id;
    public Post post;
    public Date createdDate;
    public User user;
    public String comment;

    public Comment(Post post, Date createdDate, User user, String comment) {
        this.post = post;
        this.createdDate = createdDate;
        this.user = user;
        this.comment = comment;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}