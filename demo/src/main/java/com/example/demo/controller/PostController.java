package com.example.demo.controller;

import com.example.demo.entity.ApplicationUser;
import com.example.demo.Repository.ApplicationUserRepository;
import com.example.demo.entity.Post;
import com.example.demo.Repository.PostRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import java.security.Principal;

@Controller
public class PostController {
    @Autowired
    PostRepositry postRepositry;
    @Autowired
    ApplicationUserRepository applicationUserRepository;


    @GetMapping("/addposts")
    public String getAddPost(Principal principal , String body , Model model){
        ApplicationUser newUser = applicationUserRepository.findByUsername(principal.getName());
        System.out.println(newUser.getPosts()+"===========================================================");
        model.addAttribute("posts" ,newUser.getPosts());
        ApplicationUser requiredProfile = applicationUserRepository.findByUsername(principal.getName());
        model.addAttribute("requireduser", requiredProfile);
        return "profile.html";
    }
    @PostMapping("/addpost")
        public RedirectView addPost(Principal principal , String body , Model model){
        ApplicationUser newUser = applicationUserRepository.findByUsername(principal.getName());
        Post post = new Post(body,newUser);
        postRepositry.save(post);
//       model.addAttribute("posts" ,newUser.getPosts());
        return new RedirectView("/addposts");
    }

}
