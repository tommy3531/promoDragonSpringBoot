package com.tommarler.growthDragon.domain;

import java.util.ArrayList;

// User who made a post on a comment
public class UserPostComment {

    public User user;
    public Post post;
    public Comment comment;
    public UserPost userPost;

    public UserPostComment() {}

    public UserPostComment(User user, Post post, Comment comment, UserPost userPost) {
        this.user = user;
        this.post = post;
        this.comment = comment;
        this.userPost = userPost;
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

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public UserPost getUserPost() {
        return userPost;
    }

    public void setUserPost(UserPost userPost) {
        this.userPost = userPost;
    }
}
