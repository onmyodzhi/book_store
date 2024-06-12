package com.sidorov.pet.book_store.controllers.ui.interfaces;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public interface OrderController {

    @GetMapping
    String getAllOrders(Model model, @AuthenticationPrincipal UserDetails userDetails);

    @PostMapping("/add")
    String addOrder(@AuthenticationPrincipal UserDetails userDetails);


}
