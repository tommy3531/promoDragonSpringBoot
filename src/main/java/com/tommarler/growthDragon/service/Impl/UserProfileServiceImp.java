package com.tommarler.growthDragon.service.Impl;

import com.tommarler.growthDragon.domain.User;
import com.tommarler.growthDragon.domain.UserProfile;
import com.tommarler.growthDragon.repository.UserProfileRepository;
import com.tommarler.growthDragon.repository.UserRepository;
import com.tommarler.growthDragon.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImp implements UserProfileService {

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserProfile save(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

    @Override
    public UserProfile findUserDetailsForUser(User user) {
        UserProfile loggedInUserHasProfile = userProfileRepository.findUserFromProfile(user);
        if(loggedInUserHasProfile == null) {
            UserProfile userProfile = new UserProfile();
            return userProfile;
        }
        else {
            System.out.println("Logged in user: " + user.getId());
            System.out.println("Found user: " + loggedInUserHasProfile.getUser().getId());
            return loggedInUserHasProfile;
        }
    }
}
