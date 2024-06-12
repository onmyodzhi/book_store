package com.sidorov.pet.book_store.controllers.ui.interfaces;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface CartController {
    @GetMapping
    String showCart(Model model, @AuthenticationPrincipal UserDetails userDetails);

    @PostMapping("/add")
    String addToCart(@AuthenticationPrincipal UserDetails userDetails, @RequestParam Long bookId);

    @PostMapping("/update")
    String updateCart(@AuthenticationPrincipal UserDetails userDetails,
                      @RequestParam Long bookId, @RequestParam Integer quantity);

    @PostMapping("/delete")
    String deleteCart(@AuthenticationPrincipal UserDetails userDetails, @RequestParam Long bookId);
}
