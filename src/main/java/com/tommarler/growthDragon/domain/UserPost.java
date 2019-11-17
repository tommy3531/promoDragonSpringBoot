package com.tommarler.growthDragon.domain;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;

// The user who created a post
public class UserPost {

    @Id
    private String id;
    private User user;
    private Post post;
    private ArrayList<User> users;
    private ArrayList<Post> posts;

    public UserPost() {}

    public UserPost(String id, User user, Post post, ArrayList<User> users, ArrayList<Post> posts) {
        this.id = id;
        this.user = user;
        this.post = post;
        this.users = users;
        this.posts = posts;
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

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }
}
