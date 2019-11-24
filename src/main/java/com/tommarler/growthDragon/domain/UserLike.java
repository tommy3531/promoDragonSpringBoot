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
    public UserPost userPost;
    public boolean linkedToPost;

    public UserLike() {}

    public UserLike(String id, UserPost userPost, boolean linkedToPost) {
        this.id = id;
        this.userPost = userPost;
        this.linkedToPost = linkedToPost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserPost getUserPost() {
        return userPost;
    }

    public void setUserPost(UserPost userPost) {
        this.userPost = userPost;
    }

    public boolean isLinkedToPost() {
        return linkedToPost;
    }

    public void setLinkedToPost(boolean linkedToPost) {
        this.linkedToPost = linkedToPost;
    }
}
