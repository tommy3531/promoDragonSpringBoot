package com.tommarler.growthDragon.service.Impl;

import com.tommarler.growthDragon.domain.UserLike;
import com.tommarler.growthDragon.repository.UserLikeRepository;
import com.tommarler.growthDragon.service.UserLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLikeServiceImp implements UserLikeService {

    @Autowired
    public UserLikeRepository userLikeRepository;

    @Override
    public List<UserLike> findAll() {
        return null;
    }

    @Override
    public UserLike save(UserLike userLike) {
        return userLikeRepository.save(userLike);
    }
}
