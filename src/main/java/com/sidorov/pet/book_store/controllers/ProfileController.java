package com.sidorov.pet.book_store.controllers;

import com.sidorov.pet.book_store.entities.User;
import com.sidorov.pet.book_store.services.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/profile")
@AllArgsConstructor(onConstructor_ = {@Autowired})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileController {

    UserService userService;

    @GetMapping
    public String showProfilePage(Model model, Principal principal) {
        User user = userService.getUserByUsername(principal.getName()).get();
        model.addAttribute("fullName", user.getUsername());
        model.addAttribute("email", user.getEmail());

        return "profile-page";
    }
}
