package com.tommarler.growthDragon.service.Impl;

import com.tommarler.growthDragon.domain.Post;
import com.tommarler.growthDragon.repository.PostRepository;
import com.tommarler.growthDragon.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public List<Post> findLatest5() {
        return null;
    }

    @Override
    public Post findById(Long id) {
        return null;
    }

    @Override
    public Post create(Post post) {
        this.posts.add(post);
        save(post);
        return post;
    }

    @Override
    public Post edit(Post post) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }
}
