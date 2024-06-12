package com.sidorov.pet.book_store.controllers.ui.interfaces;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

public interface ProfileController {
    @GetMapping
    String showProfilePage(Model model, Principal principal);
}
