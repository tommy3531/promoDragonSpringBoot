package com.tommarler.growthDragon.controller;

import com.tommarler.growthDragon.domain.Like;
import com.tommarler.growthDragon.domain.Post;
import com.tommarler.growthDragon.domain.PostLike;
import com.tommarler.growthDragon.domain.User;
import com.tommarler.growthDragon.service.LikeService;
import com.tommarler.growthDragon.service.PostLikeService;
import com.tommarler.growthDragon.service.PostService;
import com.tommarler.growthDragon.service.UserService;
import org.bson.types.ObjectId;
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
import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user/post")
public class PostController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private LikeService likeService;

    @Autowired
    private PostLikeService postLikeService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView allPost() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("post", postService.findAll());
        modelAndView = authService("/user/newsFeed");
        return modelAndView;
    }

    @RequestMapping(value = "/like/{id}", method = RequestMethod.GET)
    public RedirectView postLike(@PathVariable("id") String id, Principal principal, Model model ) {

        // Post knows about likes
        Optional<Post> post = postService.findForId(id);
        Post postData = post.get();
        int totalLikeCount = postData.getTotalLikeCount();

        // PostLike
        List<PostLike> postLike = postLikeService.findAll();



        // Incrementer for post count
        int totalPostLikes = totalLikeCount + 1;
        int personLikeCount = 0;
        int increaseLikeCount = personLikeCount + 1;

        // Need to get a user
        User user = userService.findUserByEmail(principal.getName());

        // holder for post likes
        ArrayList<Like> likes = new ArrayList<>();
        ArrayList<Post> posts = new ArrayList<>();
        return new RedirectView("/user/newsFeed");

    }


    @RequestMapping(value = "/dislike/{id}", method = RequestMethod.GET)
    public RedirectView postUnLike(@PathVariable("id") String id, Principal principal, Model model ) {
        Optional<Post> post = postService.findForId(id);
        Post postId = post.get();
        int postCount = post.get().getTotalLikeCount();

        User user = userService.findUserByEmail(principal.getName());
        List<Like> likeServiceAll = likeService.findAll();
//        if(postId.getId() != null && likeServiceAll.size() > 0) {
//            for(Like likeItem: likeServiceAll){
//                int likeCount = likeItem.getLikeCount();
//                if(likeCount == 0) {
//                    return new RedirectView("/user/newsFeed");
//                }
//                int like = postCount - 1;
//                System.out.println("Post and Like present, WOW");
//                System.out.println("User who liked Post: " + likeItem.getUser().getId());
//                System.out.println("Current Logged in user: " + user.getId());
//                likeItem.setUser(user);
//                likeItem.setLikeCount(like);
//                postId.setLikeCount(like);
//                postService.save(postId);
//                likeService.save(likeItem);
//                return new RedirectView("/user/newsFeed");
//            }
//
//            return new RedirectView("/user/newsFeed");
//        } else {
//            int like = postCount - 1;
//            Like likeData = new Like();
//            likeData.setUser(user);
//            likeData.setLikeCount(like);
//            postId.setLikeCount(like);
//            postService.save(postId);
//            likeService.save(likeData);
//            return new RedirectView("/user/newsFeed");
//        }
            return new RedirectView("/user/newsFeed");


    }

    @RequestMapping(value = "/newPost", method = RequestMethod.GET)
    public ModelAndView newPost(Principal principal, Model model) {
        String postFormString = "/user/post/postForm";
        ModelAndView postFormView;

        User user = userService.findUserByEmail(principal.getName());
        ArrayList<Post> posts = new ArrayList<>();

        if (user.isEnabled()) {
            Post post = new Post();
            posts.add(post);
//            post.setUser(user);

            String localDate = generateDate();
            post.setCreatedDate(localDate);
            postFormView = authService(postFormString);
            postFormView.addObject("post", post);
            userService.saveUser(user);
            return postFormView;
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("login");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/newPost", method = RequestMethod.POST)
    public RedirectView createNewPost(@Valid Post post, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new RedirectView("/user/post/postForm");
        } else {
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

}
