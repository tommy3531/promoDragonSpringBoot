package com.tommarler.growthDragon.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "postlike")
public class PostLike {

    public ArrayList<Post> posts;
    public ArrayList<Like> likes;

    public PostLike(ArrayList<Post> posts, ArrayList<Like> likes) {
        this.posts = posts;
        this.likes = likes;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public ArrayList<Like> getLikes() {
        return likes;
    }

    public void setLikes(ArrayList<Like> likes) {
        this.likes = likes;
    }
}
