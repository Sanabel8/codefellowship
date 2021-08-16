package com.example.demo.controller;

import com.example.demo.entity.ApplicationUser;
import com.example.demo.entity.ApplicationUserRepository;
import com.example.demo.entity.Post;
import com.example.demo.entity.PostRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PostController {
    @Autowired
    PostRepositry postRepositry;
    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @PostMapping("/addpost")
    public RedirectView addPost(Principal principal , String body , Model model){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date timeStamp = new Date();
        ApplicationUser newUser = applicationUserRepository.findByUsername(principal.getName());

        Post post = new Post(body,timeStamp,newUser);

        postRepositry.save(post);
       model.addAttribute("posts" ,newUser.getPosts());
        return new RedirectView("/user");
    }


}