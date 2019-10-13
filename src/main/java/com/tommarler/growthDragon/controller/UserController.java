package com.tommarler.growthDragon.controller;

import com.tommarler.growthDragon.domain.Post;
import com.tommarler.growthDragon.domain.User;
import com.tommarler.growthDragon.domain.UserProfileDetails;
import com.tommarler.growthDragon.service.CommentService;
import com.tommarler.growthDragon.service.PostService;
import com.tommarler.growthDragon.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    public PostService postService;

    @Autowired
    public CommentService commentService;

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
        newsFeedView.addObject("post", postService.findAll());
        newsFeedView.addObject("comment", commentService.findAll());
        return newsFeedView;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profile(Principal principal, Model model) {
        String profileString = "/user/profile";
        User user = userService.findUserByEmail(principal.getName());
        ModelAndView profileView = new ModelAndView();
        profileView = authService(profileString);
        profileView.addObject("currentUser", user);
        return profileView;
    }

    @RequestMapping(value = "/editProfile", method = RequestMethod.GET)
    public ModelAndView newPost(Principal principal, Model model) {
        String userProfileDetailsForm = "/user/createProfileForm";
        ModelAndView userProfileDetailFormView;

        User user = userService.findUserByEmail(principal.getName());

        if (user.isEnabled()) {
            UserProfileDetails userProfileDetails = new UserProfileDetails();
            userProfileDetails.setUser(user);
            userProfileDetailFormView = authService(userProfileDetailsForm);
            userProfileDetailFormView.addObject("userProfileDetails", userProfileDetails);
            userProfileDetailFormView.addObject("user", user);
            return userProfileDetailFormView;
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("login");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/editProfile/{id}", method = RequestMethod.GET)
    public ModelAndView createPostComment(@PathVariable("id") String id, Principal principal, Model model) {
        String creatPostString = "/user/createProfileForm";
        ModelAndView userDetailView = new ModelAndView();
        userDetailView.setViewName(creatPostString);
        User user = userService.findUserByEmail(principal.getName());
        UserProfileDetails userProfileDetails = new UserProfileDetails();
        userProfileDetails.setUser(user);
        userDetailView.addObject("user", user);
        userDetailView.addObject("userProfileDetails", userProfileDetails);
        System.out.println("Current User ID: " + id);
        return userDetailView;
//        if (user.isEnabled() && (user.id == id) {
//            UserProfileDetails userDetails = new UserProfileDetails();
//            Optional<Post> post = postService.findForId(id);
//            if(post.isPresent()){
//                comment.setPost(post.get());
//                comment.setUser(user);
//                commentPostIdView = new ModelAndView();
//                commentPostIdView.setViewName(creatPostString);
//                commentPostIdView = authService(creatPostString);
//                commentPostIdView.addObject("comment", comment);
//                return commentPostIdView;
//            } else {
//                commentPostIdView = new ModelAndView();
//                commentPostIdView.setViewName("/home");
//                return commentPostIdView;            }
//
//        } else {
//            commentPostIdView = new ModelAndView();
//            commentPostIdView.setViewName("/home");
//            return commentPostIdView;
//        }
    }
}
