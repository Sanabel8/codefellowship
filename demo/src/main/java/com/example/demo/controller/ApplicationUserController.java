package com.example.demo.controller;

import com.example.demo.Repository.PostRepositry;
import com.example.demo.entity.ApplicationUser;
import com.example.demo.Repository.ApplicationUserRepository;
import com.example.demo.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.Set;

@Controller
public class ApplicationUserController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    PostRepositry postRepositry;


    // for authintication
    @GetMapping("/signup")
    public String getSignUpPage() {
        return "signup.html";
    }

    @GetMapping("/login")
    public String getSignInPage() {
        return "signin.html";
    }

    @PostMapping("/signup")
    public RedirectView signUp(@RequestParam(value = "username") String username,
                               @RequestParam(value = "password") String password,
                               @RequestParam(value = "firstName") String firstName,
                               @RequestParam(value = "lastName") String lastName,
                               @RequestParam(value = "password") String dateOfBirth,
                               @RequestParam(value = "bio") String bio) {
        ApplicationUser newUser = new ApplicationUser(username, bCryptPasswordEncoder.encode(password), firstName, lastName, dateOfBirth, bio);
        applicationUserRepository.save(newUser);
        return new RedirectView("/signup");
    }


    // for user
    @GetMapping("/users/{id}")
    public String getUserProfile(Principal principal,Model model, @PathVariable Integer id) {
        ApplicationUser user = applicationUserRepository.findById(id).get();
        model.addAttribute("requireduser",user);

//        ApplicationUser newUser = applicationUserRepository.findByUsername(principal.getName());
//        System.out.println(newUser.getPosts());
//        model.addAttribute("posts" ,newUser.getPosts());

        return "profile.html";
    }


//    @PostMapping(value = "/user")
//    public RedirectView addPost(Principal principal, @RequestParam(value = "body") String body, Model model) {
//        Post post = new Post(body, applicationUserRepository.findByUsername(principal.getName()));
////        model.addAttribute("userData" , principal.getName());
////        model.addAttribute("userProfile" , applicationUserRepository.findByUsername(principal.getName()));
//        postRepositry.save(post);
//        return new RedirectView("/profile");
//    }

    @GetMapping("/profile")
    public String getProfilePage(Principal p, Model m) {
        ApplicationUser requiredProfile = applicationUserRepository.findByUsername(p.getName());
        if (requiredProfile != null) {
            m.addAttribute("requireduser", requiredProfile);
            String requiredProfileUserName = requiredProfile.getUsername();
            String loggedInUserName = p.getName();
            boolean isLoggedInUserPofile = requiredProfileUserName.equals(loggedInUserName);
            m.addAttribute("isLoggedInUserPofile", isLoggedInUserPofile);

        } else {
            System.out.println("error messege");
        }
        return "profile.html";
    }

    // for follow
    @PostMapping("/follow")
    public RedirectView addFollow(Principal principal, @RequestParam(value = "id") int id) {
        ApplicationUser forName = applicationUserRepository.findByUsername(principal.getName());
        ApplicationUser forId = applicationUserRepository.findById(id).get();
        forName.getFollowers().add(forId);

        applicationUserRepository.save(forName);
        return new RedirectView("/feed");
    }

    @GetMapping("/feed")
    public String getFollowingData(Principal principal, Model model) {
        model.addAttribute("userData", principal.getName());

        ApplicationUser applicationUser = applicationUserRepository.findByUsername(principal.getName());
//        Set<ApplicationUser> myFollowing = applicationUser.getFollowers();
        model.addAttribute(("allMyFolwing"), applicationUser.getFollowers());
        return ("/feed.html");
    }




}