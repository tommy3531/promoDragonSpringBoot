package com.tommarler.growthDragon.service;

import com.tommarler.growthDragon.domain.User;
import com.tommarler.growthDragon.domain.UserProfile;

public interface UserProfileService {

    UserProfile save(UserProfile userProfile);
    UserProfile findUserDetailsForUser(User user);
}
