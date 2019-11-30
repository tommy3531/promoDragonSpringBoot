package com.tommarler.growthDragon.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "userlike")
public class UserLike {

    @Id
    public String id;
    public int postId;
    public User user;
    public UserPost userPost;
    public boolean linkedToPost;
    public boolean isLiked;

    public UserLike() {}

    public UserLike(String id, int postId, User user, UserPost userPost, boolean linkedToPost, boolean isLiked) {
        this.id = id;
        this.postId = postId;
        this.user = user;
        this.userPost = userPost;
        this.linkedToPost = linkedToPost;
        this.isLiked = isLiked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserPost getUserPost() {
        return userPost;
    }

    public void setUserPost(UserPost userPost) {
        this.userPost = userPost;
    }

    public boolean getIsLinkedToPost() {
        return linkedToPost;
    }

    public void setLinkedToPost(boolean linkedToPost) {
        this.linkedToPost = linkedToPost;
    }

    public boolean getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(boolean liked) {
        isLiked = liked;
    }
}
