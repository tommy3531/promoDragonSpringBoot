package com.tommarler.growthDragon.service;

import com.tommarler.growthDragon.domain.UserLike;

import java.util.List;

public interface UserLikeService {
    List<UserLike> findAll();
    UserLike save(UserLike postLike);
}
