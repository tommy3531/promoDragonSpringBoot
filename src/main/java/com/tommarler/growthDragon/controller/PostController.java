package com.tommarler.growthDragon.controller;

import com.tommarler.growthDragon.domain.Post;
import com.tommarler.growthDragon.domain.User;
import com.tommarler.growthDragon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping("/user/post")
public class PostController {


    @Autowired
    private UserService userService;


    @RequestMapping(value = "/newPost", method = RequestMethod.GET)
    public String newPost(Principal principal,
                          Model model) {

        User user = (User) userService.loadUserByUsername(principal.getName());

        if (user.isEnabled()) {
            Post post = new Post();
            post.setUser(user);

            model.addAttribute("post", post);

            return "/postForm";

        } else {
            return "/error";
        }
    }

}
