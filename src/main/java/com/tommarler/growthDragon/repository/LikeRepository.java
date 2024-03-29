package com.tommarler.growthDragon.repository;

import com.tommarler.growthDragon.domain.UserLike;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends MongoRepository<UserLike, String> {
}
