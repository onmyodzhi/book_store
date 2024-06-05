package com.sidorov.pet.book_store.controllers;

import com.sidorov.pet.book_store.beans.Cart;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MainController {

    Cart cart;

    @GetMapping
    public String showHomePage() {
        return "about-page";
    }
}
