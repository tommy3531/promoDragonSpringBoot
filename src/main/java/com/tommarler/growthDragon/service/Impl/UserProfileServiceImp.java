package com.tommarler.growthDragon.service.Impl;

import com.tommarler.growthDragon.domain.User;
import com.tommarler.growthDragon.domain.UserProfileDetails;
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
    public UserProfileDetails save(UserProfileDetails userProfileDetails) {
        return userProfileRepository.save(userProfileDetails);
    }

    @Override
    public UserProfileDetails findUserDetailsForUser(User user) {
        UserProfileDetails loggedInUserHasProfile = userProfileRepository.findUserFromProfile(user);
        if(loggedInUserHasProfile == null) {
            UserProfileDetails userProfileDetails = new UserProfileDetails();
            return userProfileDetails;
        }
        else {
            System.out.println("Logged in user: " + user.getId());
            System.out.println("Found user: " + loggedInUserHasProfile.getUser().getId());
            return loggedInUserHasProfile;
        }
    }
}
