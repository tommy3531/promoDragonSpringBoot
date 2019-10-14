package com.tommarler.growthDragon.service;

import com.tommarler.growthDragon.domain.User;
import com.tommarler.growthDragon.domain.UserProfileDetails;

public interface UserProfileService {

    UserProfileDetails save(UserProfileDetails userProfileDetails);
    UserProfileDetails findUserDetailsForUser(User user);
}
