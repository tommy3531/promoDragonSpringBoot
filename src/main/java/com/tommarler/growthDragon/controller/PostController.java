package com.tommarler.growthDragon.controller;

import com.tommarler.growthDragon.domain.Like;
import com.tommarler.growthDragon.domain.Post;
import com.tommarler.growthDragon.domain.User;
import com.tommarler.growthDragon.service.LikeService;
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
        int postCount = postData.getLikeCount();
        int like = postCount + 1;

        // Need to get a user
        User user = userService.findUserByEmail(principal.getName());

        // holder for post likes
        ArrayList<Like> likes = new ArrayList<>();

        // If user owns the post
        if (user.id == postData.getUser().getId()) {
            // Dont let them like it
            return new RedirectView("/user/newsFeed");

        } else {
            // This user does not own the post
            for (Like likePost : postData.getLikes()) {
                // See if user has liked the post
                String userWhoLikedPost = likePost.getUser().getId().toString();
                String loggedInUser = user.getId().toString();
                System.out.println("This is the user who liked the post: " + userWhoLikedPost);
                System.out.println("This is the user who is logged in:   " + loggedInUser);
                System.out.println("END");
                if (userWhoLikedPost.equals(loggedInUser)) {
                    // Dont let them like the post again
                    return new RedirectView("/user/newsFeed");

                } else {
                    // User does not own post and has not liked the post
                    // Create new like assign it to a user
                    Like newUserlike = new Like();
                    newUserlike.setUser(user);
                    newUserlike.setLikeCount(like);

                    // Loop through all user who liked the post
                    if(likePost.getUser() != null) {
                        likes.add(likePost);
                        likes.add(newUserlike);
                        postData.setLikes(likes);
                    }

                    // Save like and post
                    likeService.save(newUserlike);
                    postService.save(postData);
                    return new RedirectView("/user/newsFeed");
                }
            }
        }
        return new RedirectView("/user/newsFeed");
    }
//        }
//
//                likeItem.setUser(user);
//                likeItem.setLikeCount(like);
//                postId.setLikeCount(like);
//                likes.add(likeItem);
//                postId.setLikes(likes);
//                postService.save(postId);
//                likeService.save(likeItem);
//                for(Like item: postId.getLikes()){
//                    System.out.println("The user who liked this post: " + item.getUser().fullname);
//                    System.out.println("The user who liked this post id: " + item.getUser().getId());
//                    System.out.println("Like count: " + item.getLikeCount());
//                    System.out.println("END");
//
//                }
//            }
//
//        } else {
//            int like = postCount + 1;
//            Like likeData = new Like();
//            likeData.setUser(user);
//            likeData.setLikeCount(like);
//            postId.setLikeCount(like);
//            postService.save(postId);
//            likeService.save(likeData);
//            return new RedirectView("/user/newsFeed");
//
//        }
    //}

    @RequestMapping(value = "/dislike/{id}", method = RequestMethod.GET)
    public RedirectView postUnLike(@PathVariable("id") String id, Principal principal, Model model ) {
        Optional<Post> post = postService.findForId(id);
        Post postId = post.get();
        int postCount = post.get().getLikeCount();

        User user = userService.findUserByEmail(principal.getName());
        List<Like> likeServiceAll = likeService.findAll();
        if(postId.getId() != null && likeServiceAll.size() > 0) {
            for(Like likeItem: likeServiceAll){
                int likeCount = likeItem.getLikeCount();
                if(likeCount == 0) {
                    return new RedirectView("/user/newsFeed");
                }
                int like = postCount - 1;
                System.out.println("Post and Like present, WOW");
                System.out.println("User who liked Post: " + likeItem.getUser().getId());
                System.out.println("Current Logged in user: " + user.getId());
                likeItem.setUser(user);
                likeItem.setLikeCount(like);
                postId.setLikeCount(like);
                postService.save(postId);
                likeService.save(likeItem);
                return new RedirectView("/user/newsFeed");
            }

            return new RedirectView("/user/newsFeed");
        } else {
            int like = postCount - 1;
            Like likeData = new Like();
            likeData.setUser(user);
            likeData.setLikeCount(like);
            postId.setLikeCount(like);
            postService.save(postId);
            likeService.save(likeData);
            return new RedirectView("/user/newsFeed");
        }

    }

    @RequestMapping(value = "/newPost", method = RequestMethod.GET)
    public ModelAndView newPost(Principal principal, Model model) {
        String postFormString = "/user/post/postForm";
        ModelAndView postFormView;

        User user = userService.findUserByEmail(principal.getName());

        if (user.isEnabled()) {
            Post post = new Post();
            post.setUser(user);
            String localDate = generateDate();
            post.setCreatedDate(localDate);
            postFormView = authService(postFormString);
            postFormView.addObject("post", post);
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
