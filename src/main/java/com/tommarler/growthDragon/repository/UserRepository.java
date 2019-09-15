package com.tommarler.growthDragon.repository;

import com.tommarler.growthDragon.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);
}
