package com.tommarler.growthDragon.domain;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;

// The user who created a post
public class UserPost {

    @Id
    private String id;
    private User user;
    private Post post;
    private ArrayList<UserPost> userPosts;

    public UserPost() {}

    public UserPost(String id, User user, Post post, ArrayList<UserPost> userPosts) {
        this.id = id;
        this.user = user;
        this.post = post;
        this.userPosts = userPosts;
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

    public ArrayList<UserPost> getUserPosts() {
        return userPosts;
    }

    public void setUserPosts(ArrayList<UserPost> userPosts) {
        this.userPosts = userPosts;
    }
}
