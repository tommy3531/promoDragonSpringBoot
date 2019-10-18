package com.tommarler.growthDragon.domain;

public class Like {
    private User user;
    private Post post;
    private int likeCount;

    public Like(User user, Post post, int likeCount) {
        this.user = user;
        this.post = post;
        this.likeCount = likeCount;
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

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
}
