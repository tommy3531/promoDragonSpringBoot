package com.tommarler.growthDragon.controller;

import com.tommarler.growthDragon.domain.Like;
import com.tommarler.growthDragon.domain.Post;
import com.tommarler.growthDragon.domain.User;
import com.tommarler.growthDragon.service.LikeService;
import com.tommarler.growthDragon.service.PostService;
import com.tommarler.growthDragon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class LikeController {

    @Autowired
    public LikeService likeService;

    @Autowired
    public UserService userService;

    @Autowired
    public PostService postService;

    @RequestMapping(value = "/like/{id}", method = RequestMethod.GET)
    public RedirectView postLike(@PathVariable("id") String id, Principal principal, Model mode ) {
        Optional<Post> postObject = postService.findForId(id);
        Post unwrappedPost = postObject.get();
        User user = userService.findUserByEmail(principal.getName());
        Like likeInfo = likeService.findLikeByUser(user);
        int likeCount = likeInfo.getLikeCount();
        if(likeCount == 0){
            int like = likeCount + 1;

            likeInfo.setLikeCount(like);
            likeInfo.setUser(user);
            likeInfo.setPost(unwrappedPost);
            likeService.save(likeInfo);
            return new RedirectView("/user/newsFeed");

        } else {
            int like = likeCount + 1;

            likeInfo.setLikeCount(like);
            likeInfo.setUser(user);
            likeInfo.setPost(unwrappedPost);
            likeService.save(likeInfo);
            return new RedirectView("/user/newsFeed");
        }
    }

    @RequestMapping(value = "/dislike/{id}", method = RequestMethod.GET)
    public RedirectView postDisLike(@PathVariable("id") String id, Principal principal, Model mode ) {
        Optional<Post> postObject = postService.findForId(id);
        Post unwrappedPost = postObject.get();
        User user = userService.findUserByEmail(principal.getName());
        Like likeInfo = likeService.findLikeByUser(user);
        int likeCount = likeInfo.getLikeCount();
        if(likeCount == 0){
            int like = likeCount + 1;

            likeInfo.setLikeCount(like);
            likeInfo.setUser(user);
            likeInfo.setPost(unwrappedPost);
            likeService.save(likeInfo);
            return new RedirectView("/user/newsFeed");

        } else {
            int like = likeCount - 1;

            likeInfo.setLikeCount(like);
            likeInfo.setUser(user);
            likeInfo.setPost(unwrappedPost);
            likeService.save(likeInfo);
            return new RedirectView("/user/newsFeed");
        }
    }
}
