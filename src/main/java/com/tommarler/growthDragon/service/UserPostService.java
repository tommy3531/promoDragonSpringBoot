package com.tommarler.growthDragon.service;

import com.tommarler.growthDragon.domain.Post;
import com.tommarler.growthDragon.domain.User;
import com.tommarler.growthDragon.domain.UserPost;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserPostService {
    UserPost save(UserPost userPost);
    List<UserPost> findAll();
    List<Post> findByUser(User user);
    UserPost findUserPostByUser(User user);
    UserPost findUserPostByUserPostId(int userPostId);
    UserPost saveAll(List<UserPost> userPosts);
}
