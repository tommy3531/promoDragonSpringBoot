package com.tommarler.growthDragon.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "like")
public class Like {
    @Id
    private User user;
    @Indexed(name = "post", direction = IndexDirection.DESCENDING)
    private Post post;
    public int likeCount;

    public Like(User user, Post post, int likeCount) {
        this.user = user;
        this.post = post;
        this.likeCount = likeCount;
    }

    public Like() {}

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

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
}
