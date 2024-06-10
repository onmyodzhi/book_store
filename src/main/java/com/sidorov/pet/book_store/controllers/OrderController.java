package com.sidorov.pet.book_store.controllers;

import com.sidorov.pet.book_store.entities.Cart;
import com.sidorov.pet.book_store.entities.Order;
import com.sidorov.pet.book_store.entities.User;
import com.sidorov.pet.book_store.services.CartService;
import com.sidorov.pet.book_store.services.OrderService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/order")
@AllArgsConstructor(onConstructor_ = {@Autowired})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderController {

    OrderService orderService;
    CartService cartService;
    UserService userService;

    @GetMapping
    public String getAllOrders(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return "redirect:/login";
        }

        User user = userService.getUserByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<Order> orders = orderService.findAllByUserId(user.getId());
        model.addAttribute("orders", orders);
        return "order-page";
    }

    @PostMapping("/add")
    public String addOrder(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return "redirect:/login";
        }

        User user = userService.getUserByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<Cart> orderItems = cartService.getAllCartByUserId(user.getId());

        orderService.saveAllFromCart(orderItems);
        return "redirect:/order";
    }
}
