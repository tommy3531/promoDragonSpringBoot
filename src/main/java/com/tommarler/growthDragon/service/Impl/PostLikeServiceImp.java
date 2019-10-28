package com.tommarler.growthDragon.service.Impl;

import com.tommarler.growthDragon.domain.PostLike;
import com.tommarler.growthDragon.repository.PostLikeRepository;
import com.tommarler.growthDragon.service.PostLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostLikeServiceImp implements PostLikeService {

    @Autowired
    public PostLikeRepository postLikeRepository;

    @Override
    public List<PostLike> findAll() {
        return null;
    }

    @Override
    public PostLike save(PostLike postLike) {
        return postLikeRepository.save(postLike);
    }
}
