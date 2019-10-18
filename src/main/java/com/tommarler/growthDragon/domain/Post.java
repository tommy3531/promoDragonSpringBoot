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
    public int likes;
    public User user;
    public Post() {}

    public Post(ObjectId id, String title, String content, String createdDate, int likes, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.likes = likes;
        this.user = user;
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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
