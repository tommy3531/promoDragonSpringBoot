package com.tommarler.growthDragon.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "post")
public class Post {

    @Id
    private ObjectId id;
    public String title;
    public String content;
    public String createdDate;
    public User user;
    public int likeCount;
    public Post() {}

    public Post(ObjectId id, String title, String content, String createdDate, User user, int likeCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.user = user;
        this.likeCount = likeCount;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
