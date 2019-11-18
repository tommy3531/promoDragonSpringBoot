package com.tommarler.growthDragon.domain;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;

// The user who created a post
public class UserPost {

    @Id
    private String id;
    private User user;
    private Post post;

    public UserPost() {}

    public UserPost(String id, User user, Post post) {
        this.id = id;
        this.user = user;
        this.post = post;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
