package com.tommarler.growthDragon.controller;

import com.tommarler.growthDragon.domain.Post;
import com.tommarler.growthDragon.domain.User;
import com.tommarler.growthDragon.domain.UserLike;
import com.tommarler.growthDragon.domain.UserPost;
import com.tommarler.growthDragon.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.security.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

// UserPost Controller
@Controller
@RequestMapping("/user/post")
public class PostController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserPostService userPostService;

    @Autowired
    private PostService postService;

    @Autowired
    private LikeService likeService;

    @Autowired
    private UserLikeService userLikeService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView allPost() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("post", postService.findAll());
        modelAndView = authService("/user/newsFeed");
        return modelAndView;
    }

    @RequestMapping(value = "/like/{id}", method = RequestMethod.GET)
    public RedirectView postLike(@PathVariable("id") String id, Principal principal, Model model ) {
        System.out.println("This is the post Id: " + id);
        User user = userService.findUserByEmail(principal.getName());
        Optional<Post> post = postService.findForId(id);
        Post postData = post.get();
        UserPost userPost = userPostService.findUserPostByUserPostId(postData.getUserPostId());

        // Find post from ID
        UserLike userLikeByUser = userLikeService.findUserLikeByUserId(user.getId());
        System.out.println("END");
        // Check if post is linked to like
        if(userLikeByUser != null){
            // check if current user liked the post
            System.out.println("User like" + userLikeByUser.getUser().getId());
            System.out.println("Logged in user: " + user.getId());
            System.out.println("END");

            if(userLikeByUser.getUser().getId().equals(user.id)){
                System.out.println("User has already liked the post: " + user.getFullname());
            } else {
                System.out.println("User has not liked post: " + user.getFullname());
                System.out.println("END");
            }
        } else {
            // Create userLike
            UserLike userLike = new UserLike();
            userLike.setUser(user);
            userLike.setPostId(postData.getUserPostId());
            userLike.setLinkedToPost(true);
            userLikeService.save(userLike);
            System.out.println("Saved userLike: ");
        }


        // PostLike
        List<UserLike> postLike = userLikeService.findAll();

        // Incrementer for post count
        int personLikeCount = 0;
        int increaseLikeCount = personLikeCount + 1;
        // holder for post likes
        ArrayList<UserLike> userLikes = new ArrayList<>();
        ArrayList<Post> posts = new ArrayList<>();
        return new RedirectView("/user/newsFeed");
    }


    @RequestMapping(value = "/dislike/{id}", method = RequestMethod.GET)
    public RedirectView postUnLike(@PathVariable("id") String id, Principal principal, Model model ) {
        System.out.println("This is the post Id: " + id);

        Optional<Post> post = postService.findForId(id);

        User user = userService.findUserByEmail(principal.getName());
        List<UserLike> userLikeServiceAll = likeService.findAll();

        return new RedirectView("/user/newsFeed");
    }

    @RequestMapping(value = "/newPost", method = RequestMethod.GET)
    public ModelAndView newPost(Principal principal, Model model) throws NoSuchProviderException, NoSuchAlgorithmException {
        String localDate = generateDate();

        String postFormString = "/user/post/postForm";
        ModelAndView postFormView;

        User user = userService.findUserByEmail(principal.getName());

        if (user.isEnabled()) {

            // Create a new Post
            Post post = new Post();
            post.setCreatedDate(localDate);
            int number = secureRandomNumber();
            post.setUserPostId(number);

            postFormView = authService(postFormString);
            postFormView.addObject("posts", post);
            return postFormView;
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("login");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/newPost", method = RequestMethod.POST)
    public RedirectView createNewPost(@Valid Post post, Principal principal, BindingResult bindingResult) throws NoSuchProviderException, NoSuchAlgorithmException {
        User user = userService.findUserByEmail(principal.getName());
        List<Post> posts = userPostService.findByUser(user);
        List<Post> postList = new ArrayList<>();
        UserPost userPost = new UserPost();

        if (bindingResult.hasErrors()) {
            return new RedirectView("/user/post/postForm");
        } else {

            post.setLinkedToUserPost(true);
            userPost.setUserPostId(post.getUserPostId());
            userPost.setPost(post);
            userPost.setUser(user);
            userPostService.save(userPost);
            postService.save(post);
            return new RedirectView("/user/newsFeed");

        }
    }

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



    private String generateDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        String dateStr = dtf.format(localDate);
        return dateStr;
    }

    private int secureRandomNumber() {
        Random rand = new Random(System.currentTimeMillis());
        int r = rand.nextInt();
        return r;
    }

}
