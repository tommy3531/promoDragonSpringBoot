package com.tommarler.growthDragon.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;

// The user who created a post
public class UserPost {

    @Id
    public ObjectId id;
    public int userPostId;
    public Post post;
    public User user;


    public UserPost() {}

    public UserPost(ObjectId id, int userPostId, Post post, User user) {
        this.id = id;
        this.userPostId = userPostId;
        this.post = post;
        this.user = user;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public int getUserPostId() {
        return userPostId;
    }

    public void setUserPostId(int userPostId) {
        this.userPostId = userPostId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
