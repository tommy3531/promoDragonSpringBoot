package com.tommarler.growthDragon.repository;

import com.tommarler.growthDragon.domain.PostLike;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostLikeRepository extends MongoRepository<PostLike, String> {
}
