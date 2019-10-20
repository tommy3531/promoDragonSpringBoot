package com.tommarler.growthDragon.service;

import com.tommarler.growthDragon.domain.Like;
import com.tommarler.growthDragon.domain.User;

import java.util.List;
import java.util.Optional;

public interface LikeService {
    List<Like> findAll();
    Like findLikeByUser(User user);
    //    Like create(Like like);
    Optional<Like> findForId(String id);
    Like save(Like like);
}
