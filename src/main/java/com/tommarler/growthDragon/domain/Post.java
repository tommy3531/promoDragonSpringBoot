package com.tommarler.growthDragon.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "post")
public class Post {

    @Id
    public String id;
    public int postId;
    public int userPostId;
    public int userLikeId;
    public boolean linkedToUserLike;
    public boolean linkedToUserPost;
    public String title;
    public String content;
    public String createdDate;

    public Post() {
    }

    public Post(String id, int postId, int userPostId, int userLikeId, boolean linkedToUserLike, boolean linkedToUserPost, String title, String content, String createdDate) {
        this.id = id;
        this.postId = postId;
        this.userPostId = userPostId;
        this.userLikeId = userLikeId;
        this.linkedToUserLike = linkedToUserLike;
        this.linkedToUserPost = linkedToUserPost;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
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

    public int getUserPostId() {
        return userPostId;
    }

    public void setUserPostId(int userPostId) {
        this.userPostId = userPostId;
    }

    public int getUserLikeId() {
        return userLikeId;
    }

    public void setUserLikeId(int userLikeId) {
        this.userLikeId = userLikeId;
    }

    public boolean getLinkedToUserLike() {
        return linkedToUserLike;
    }

    public void setLinkedToUserLike(boolean linkedToUserLike) {
        this.linkedToUserLike = linkedToUserLike;
    }

    public boolean isLinkedToUserPost() {
        return linkedToUserPost;
    }

    public void setLinkedToUserPost(boolean linkedToUserPost) {
        this.linkedToUserPost = linkedToUserPost;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
