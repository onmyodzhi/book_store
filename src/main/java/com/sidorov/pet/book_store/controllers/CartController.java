package com.sidorov.pet.book_store.controllers;

import com.sidorov.pet.book_store.entities.Cart;
import com.sidorov.pet.book_store.services.CartService;
import com.sidorov.pet.book_store.services.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
@AllArgsConstructor(onConstructor_ = {@Autowired})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartController {

    CartService cartService;
    UserService userService;

    @GetMapping
    public String showCart(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return "redirect:/login";
        }
        String username = userDetails.getUsername();
        Long userId = userService.getUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"))
                .getId();
        List<Cart> cart = cartService.getAllCartByUserId(userId);
        model.addAttribute("cart", cart);
        return "cart-page";
    }

    @PostMapping("/add")
    public String addToCart(@AuthenticationPrincipal UserDetails userDetails, @RequestParam Long bookId) {
        if (userDetails == null) {
            return "redirect:/login";
        }
        String username = userDetails.getUsername();
        cartService.addToCart(username, bookId);

        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String updateCart(@AuthenticationPrincipal UserDetails userDetails, @RequestParam Long bookId, @RequestParam Integer quantity) {
        if (userDetails == null) {
            return "redirect:/login";
        }
        String username = userDetails.getUsername();

        cartService.saveOrUpdate(username, bookId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/delete")
    public String deleteCart(@AuthenticationPrincipal UserDetails userDetails, @RequestParam Long bookId) {
        if (userDetails == null) {
            return "redirect:/login";
        }
        String username = userDetails.getUsername();

        cartService.delete(username, bookId);
        return "redirect:/cart";
    }
}
