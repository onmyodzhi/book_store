package com.sidorov.pet.book_store.controllers.ui.implementations;

import com.sidorov.pet.book_store.controllers.ui.interfaces.ProfileController;
import com.sidorov.pet.book_store.entities.dto.UserDTO;
import com.sidorov.pet.book_store.services.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/profile")
@AllArgsConstructor(onConstructor_ = {@Autowired})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileControllerImpl implements ProfileController {

    UserService userService;

    @Override
    public String showProfilePage(Model model, Principal principal) {
        UserDTO user = userService.getUserByUsername(principal.getName()).get();
        model.addAttribute("fullName", user.getUsername());
        model.addAttribute("email", user.getEmail());

        return "profile-page";
    }
}
