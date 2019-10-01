package com.tommarler.growthDragon.service;

import com.tommarler.growthDragon.domain.Comment;

import java.util.List;

public interface CommentService {
    Comment save(Comment comment);
    List<Comment> findAll();


}
