package com.tommarler.growthDragon.domain;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Post {

    @Id
    public String id;
    public String title;
    public String content;
    public Date createdDate;

    public Post(String title, String content, Date createdDate) {
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
