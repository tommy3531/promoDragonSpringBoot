package com.tommarler.growthDragon.service.Impl;

import com.tommarler.growthDragon.domain.Post;
import com.tommarler.growthDragon.repository.PostRepository;
import com.tommarler.growthDragon.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImp implements PostService {

    @Autowired
    public PostRepository postRepository;

    public List<Post> posts = new ArrayList<Post>();

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post create(Post post) {
        this.posts.add(post);
        save(post);
        return post;
    }

    @Override
    public Optional<Post> findForId(String id) {
        return postRepository.findById(id);
    }

    @Override
    public Post save(Post post) {

        return postRepository.save(post);
    }
}
