package com.tommarler.growthDragon.repository;

import com.tommarler.growthDragon.domain.User;
import com.tommarler.growthDragon.domain.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends MongoRepository<UserProfile, String> {
    @Query("{ 'user' : ?0 }")
    UserProfile findUserFromProfile(User user);

}
