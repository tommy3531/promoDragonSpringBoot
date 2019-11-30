package com.tommarler.growthDragon.service.Impl;

import com.tommarler.growthDragon.domain.Post;
import com.tommarler.growthDragon.domain.User;
import com.tommarler.growthDragon.domain.UserPost;
import com.tommarler.growthDragon.repository.UserPostRepository;
import com.tommarler.growthDragon.service.UserPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPostServiceImp implements UserPostService {

    @Autowired
    private UserPostRepository userPostRepository;

    @Override
    public UserPost save(UserPost userPost) {
        return userPostRepository.save(userPost);
    }

    @Override
    public List<UserPost> findAll() {
        return userPostRepository.findAll();
    }

    @Override
    public List<Post> findByUser(User user) {
        return null;
    }

    @Override
    public UserPost findUserPostByUser(User user) {
        return userPostRepository.findUserPostByUser(user);
    }

    @Override
    public UserPost findUserPostByUserPostId(int userPostId) {
        UserPost userPost = userPostRepository.findUserPostByUserPostId(userPostId);
        System.out.println(userPost);
        return userPost;
    }

    @Override
    public UserPost saveAll(List<UserPost> userPosts) {
        return (UserPost) userPostRepository.saveAll(userPosts);
    }
}
