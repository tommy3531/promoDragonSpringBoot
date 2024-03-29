package com.tommarler.growthDragon.repository;

import com.tommarler.growthDragon.domain.User;
import com.tommarler.growthDragon.domain.UserLike;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserLikeRepository extends MongoRepository<UserLike, String> {
    UserLike findUserLikeByUserId(String userId);
}
