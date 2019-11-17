package com.tommarler.growthDragon.service.Impl;

import com.tommarler.growthDragon.domain.UserLike;
import com.tommarler.growthDragon.domain.User;
import com.tommarler.growthDragon.repository.LikeRepository;
import com.tommarler.growthDragon.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeServiceImp implements LikeService {

    @Autowired
    public LikeRepository likeRepository;

    @Override
    public List<UserLike> findAll() {
        return likeRepository.findAll();
    }

    @Override
    public UserLike findLikeByUser(User user) {
//        List<Like> allLikes = likeRepository.findByUser(user);
//        for(Like item: allLikes){
//            ObjectId likeUserId = item..getId();
//            ObjectId userId = user.getId();
//            if(likeUserId.equals(userId)){
//                System.out.println("What the heck: ");
//                return item;
//            } else {
//                Like like = new Like();
//                return like;
//            }
//        }
        UserLike userLike = new UserLike();
        return userLike;

    }

    @Override
    public Optional<UserLike> findForId(String id) {
        return Optional.empty();
    }

    @Override
    public UserLike save(UserLike userLike) {
        return likeRepository.save(userLike);
    }
}
