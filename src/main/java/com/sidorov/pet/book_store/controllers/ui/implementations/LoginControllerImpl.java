package com.sidorov.pet.book_store.controllers.ui.implementations;

import com.sidorov.pet.book_store.controllers.ui.interfaces.LoginController;
import org.springframework.stereotype.Controller;

@Controller
public class LoginControllerImpl implements LoginController {
    @Override
    public String showLoginForm() {
        return "login-page";
    }
}
