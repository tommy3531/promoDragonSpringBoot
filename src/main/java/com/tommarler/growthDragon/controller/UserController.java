package com.tommarler.growthDragon.controller;

import com.tommarler.growthDragon.domain.User;
import com.tommarler.growthDragon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    private ModelAndView authService(String viewName){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("fullName", "Welcome " + user.getFullname());
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName(viewName);
        return modelAndView;
    }

    @RequestMapping(value = "/newsFeed", method = RequestMethod.GET)
    public ModelAndView newsFeed() {
        String newsFeedString = "/user/newsFeed";
        ModelAndView newsFeedView = new ModelAndView();
        newsFeedView = authService(newsFeedString);
        return newsFeedView;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profile() {
        String profileString = "/user/profile";
        ModelAndView profileView = new ModelAndView();
        profileView = authService(profileString);
        return profileView;
    }
}
