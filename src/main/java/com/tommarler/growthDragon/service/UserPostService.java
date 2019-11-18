package com.tommarler.growthDragon.service;

import com.tommarler.growthDragon.domain.UserPost;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserPostService {
    UserPost save(UserPost userPost);
    List<UserPost> findAll();
    UserPost saveAll(List<UserPost> userPosts);
}
