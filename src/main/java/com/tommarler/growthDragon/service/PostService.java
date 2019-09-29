package com.tommarler.growthDragon.service;

import com.tommarler.growthDragon.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> findAll();
    Post create(Post post);
    Optional<Post> findForId(String id);
    Post save(Post post);
}
