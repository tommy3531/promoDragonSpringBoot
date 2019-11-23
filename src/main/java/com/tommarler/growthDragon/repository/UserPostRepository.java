package com.tommarler.growthDragon.repository;

import com.tommarler.growthDragon.domain.Post;
import com.tommarler.growthDragon.domain.User;
import com.tommarler.growthDragon.domain.UserPost;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserPostRepository extends MongoRepository<UserPost, String> {
    List<Post> findUserPostByUser(User user);
}
