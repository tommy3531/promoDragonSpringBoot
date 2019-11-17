package com.tommarler.growthDragon.service;

import com.tommarler.growthDragon.domain.UserLike;
import com.tommarler.growthDragon.domain.User;

import java.util.List;
import java.util.Optional;

public interface LikeService {
    List<UserLike> findAll();
    UserLike findLikeByUser(User user);
    //    Like create(Like like);
    Optional<UserLike> findForId(String id);
    UserLike save(UserLike userLike);
}
