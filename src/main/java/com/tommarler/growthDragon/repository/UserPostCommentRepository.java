package com.tommarler.growthDragon.repository;

import com.tommarler.growthDragon.domain.UserPostComment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserPostCommentRepository extends MongoRepository<UserPostComment, String> {
}
