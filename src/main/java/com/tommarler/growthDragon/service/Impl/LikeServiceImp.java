package com.tommarler.growthDragon.service.Impl;

import com.tommarler.growthDragon.domain.Like;
import com.tommarler.growthDragon.domain.User;
import com.tommarler.growthDragon.repository.LikeRepository;
import com.tommarler.growthDragon.service.LikeService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeServiceImp implements LikeService {

    @Autowired
    public LikeRepository likeRepository;

    @Override
    public List<Like> findAll() {
        return likeRepository.findAll();
    }

    @Override
    public Like findLikeByUser(User user) {
        List<Like> allLikes = likeRepository.findByUser(user);
        for(Like item: allLikes){
            ObjectId likeUserId = item.getUser().getId();
            ObjectId userId = user.getId();
            if(likeUserId.equals(userId)){
                System.out.println("What the heck: ");
                return item;
            } else {
                Like like = new Like();
                return like;
            }
        }
        Like like = new Like();
        return like;

    }

    @Override
    public Optional<Like> findForId(String id) {
        return Optional.empty();
    }

    @Override
    public Like save(Like like) {
        return likeRepository.save(like);
    }
}
