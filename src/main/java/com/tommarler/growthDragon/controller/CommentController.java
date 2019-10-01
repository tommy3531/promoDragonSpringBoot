package com.tommarler.growthDragon.controller;

import com.tommarler.growthDragon.domain.Comment;
import com.tommarler.growthDragon.domain.Post;
import com.tommarler.growthDragon.domain.User;
import com.tommarler.growthDragon.service.CommentService;
import com.tommarler.growthDragon.service.PostService;
import com.tommarler.growthDragon.service.UserService;
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
import java.util.Optional;

@RestController
@RequestMapping("/user/comment")
public class CommentController {

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/createComment", method = RequestMethod.POST)
    public RedirectView createComment(@Valid Comment comment,
                                      BindingResult bindingResult) {

        String createForm = "/user/comment/commentForm";
        ModelAndView commentView = new ModelAndView();
        commentView.setViewName(createForm);

        if (bindingResult.hasErrors()) {
            return new RedirectView("/user/comment/commentForm");

        } else {
            commentService.save(comment);
            return new RedirectView("/user/newsFeed");
        }
    }


    @RequestMapping(value = "/newComment/{id}", method = RequestMethod.GET)
    public ModelAndView createPostComment(@PathVariable("id") String id, Principal principal, Model model) {
        String creatPostString = "/user/comment/commentForm";
        ModelAndView commentPostIdView = new ModelAndView();
        commentPostIdView.setViewName(creatPostString);
        Optional<Post> post = postService.findForId(id);
        if (post != null) {
            User user = userService.findUserByEmail(principal.getName());
            if (user.isEnabled()) {
                Comment comment = new Comment();
                comment.setUser(user);
                commentPostIdView = authService(creatPostString);
                commentPostIdView.addObject("comment", comment);
                commentPostIdView.addObject("post", post);
                return commentPostIdView;
            } else {
                commentPostIdView = new ModelAndView();
                commentPostIdView.setViewName("/home");
                return commentPostIdView;
            }
        } else {
            commentPostIdView = new ModelAndView();
            commentPostIdView.setViewName("/home");
            return commentPostIdView;
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

}
