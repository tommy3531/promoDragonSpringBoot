package com.tommarler.growthDragon.repository;

import com.tommarler.growthDragon.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PostRepository extends MongoRepository<Post, String> {
}
