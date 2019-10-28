package com.tommarler.growthDragon.service;

import com.tommarler.growthDragon.domain.PostLike;

import java.util.List;

public interface PostLikeService {
    List<PostLike> findAll();
    PostLike save(PostLike postLike);
}
