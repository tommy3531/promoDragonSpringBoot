package com.tommarler.growthDragon.repository;

import com.tommarler.growthDragon.domain.UserPost;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserPostRepository extends MongoRepository<UserPost, String> {
}
