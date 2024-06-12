package com.sidorov.pet.book_store.controllers.ui.implementations;

import com.sidorov.pet.book_store.controllers.ui.interfaces.OrderController;
import com.sidorov.pet.book_store.entities.dto.CartDTO;
import com.sidorov.pet.book_store.entities.dto.OrderDTO;
import com.sidorov.pet.book_store.entities.dto.UserDTO;
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
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/order")
@AllArgsConstructor(onConstructor_ = {@Autowired})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderControllerImpl implements OrderController {

    OrderService orderService;
    CartService cartService;
    UserService userService;

    @Override
    public String getAllOrders(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return "redirect:/login";
        }

        UserDTO user = userService.getUserByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<OrderDTO> orders = orderService.findAllByUserId(user.getId());
        model.addAttribute("orders", orders);
        return "order-page";
    }

    @Override
    public String addOrder(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return "redirect:/login";
        }

        UserDTO user = userService.getUserByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<CartDTO> orderItems = cartService.getAllCartByUserId(user.getId());

        orderService.saveAllFromCart(orderItems);
        return "redirect:/order";
    }
}
