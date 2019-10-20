package com.tommarler.growthDragon.repository;

import com.tommarler.growthDragon.domain.Like;
import com.tommarler.growthDragon.domain.User;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LikeRepository extends MongoRepository<Like, String> {
    List<Like> findByUser(User user);
}
