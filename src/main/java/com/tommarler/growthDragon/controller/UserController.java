package com.tommarler.growthDragon.controller;

import com.tommarler.growthDragon.domain.Post;
import com.tommarler.growthDragon.domain.User;
import com.tommarler.growthDragon.domain.UserPost;
import com.tommarler.growthDragon.domain.UserProfile;
import com.tommarler.growthDragon.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private UserPostService userPostService;

    @Autowired
    public PostService postService;

    @Autowired
    public CommentService commentService;

    @Autowired
    public UserProfileService userProfileService;

    @Autowired
    public LikeService likeService;

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
    public ModelAndView newsFeed(Principal principal) {
        String newsFeedString = "/user/newsFeed";
        ModelAndView newsFeedView = new ModelAndView();
        User user = userService.findUserByEmail(principal.getName());
        List<Post> posts = postService.findAll();
        for(Post item: posts){
            int userPostId = item.getUserPostId();
//            System.out.print("Posts that were written by user: " + userPost);
            System.out.println(userPostId);
        }

        newsFeedView = authService(newsFeedString);
        newsFeedView.addObject("userPosts", userPostService.findAll());
        List<UserPost> userPosts = userPostService.findAll();
        newsFeedView.addObject("user", user);
        newsFeedView.addObject("posts", posts);
        return newsFeedView;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profile(Principal principal, Model model) {
        String profileString = "/user/profile";
        User user = userService.findUserByEmail(principal.getName());
        ModelAndView profileView = new ModelAndView();
        profileView = authService(profileString);
        profileView.addObject("currentUser", user);
        profileView.addObject("userDetails", userProfileService.findUserDetailsForUser(user));
        return profileView;
    }

    @RequestMapping(value = "/editProfile", method = RequestMethod.POST)
    public RedirectView editProfile(@Valid UserProfile userProfile, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new RedirectView("/user/profile");
        } else {
            userProfileService.save(userProfile);
            return new RedirectView("/user/profile");
        }
    }

    @RequestMapping(value = "/editProfile/{id}", method = RequestMethod.GET)
    public ModelAndView createPostComment(@PathVariable("id") String id, Principal principal, Model model) {
        String creatPostString = "/user/createProfileForm";
        ModelAndView userDetailView = new ModelAndView();
        userDetailView.setViewName(creatPostString);
        User user = userService.findUserByEmail(principal.getName());
        UserProfile userProfile = new UserProfile();
        userProfile.setActive(true);
        userProfile.setUser(user);
        userDetailView.addObject("user", user);
        userDetailView.addObject("userProfileDetails", userProfile);
        System.out.println("Current User ID: " + id);
        return userDetailView;
    }
}
