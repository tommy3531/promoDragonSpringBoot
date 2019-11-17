package com.tommarler.growthDragon.domain;

import java.util.ArrayList;

// User who made a post on a comment
public class UserPostComment {

    public User user;
    public Post post;
    public Comment comment;
    public UserPost userPost;

    public ArrayList<User> users;
    public ArrayList<Post> posts;
    public ArrayList<Comment> comments;
    public ArrayList<UserPost> userPosts;

    public UserPostComment() {}

    public UserPostComment(User user, Post post, Comment comment, UserPost userPost, ArrayList<User> users, ArrayList<Post> posts, ArrayList<Comment> comments, ArrayList<UserPost> userPosts) {
        this.user = user;
        this.post = post;
        this.comment = comment;
        this.userPost = userPost;
        this.users = users;
        this.posts = posts;
        this.comments = comments;
        this.userPosts = userPosts;
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

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<UserPost> getUserPosts() {
        return userPosts;
    }

    public void setUserPosts(ArrayList<UserPost> userPosts) {
        this.userPosts = userPosts;
    }
}
