package com.tommarler.growthDragon.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Optional;

@Document(collection = "comment")
public class Comment {

    @Id
    public String id;
    public Date createdDate;
    public User user;
    public String content;

    public Comment(String id, Date createdDate, User user, String content) {
        this.id = id;
        this.createdDate = createdDate;
        this.user = user;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Comment() {}
}