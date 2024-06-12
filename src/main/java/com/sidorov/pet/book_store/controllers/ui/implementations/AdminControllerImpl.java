package com.sidorov.pet.book_store.controllers.ui.implementations;

import com.sidorov.pet.book_store.controllers.ui.interfaces.AdminController;
import org.springframework.stereotype.Controller;

@Controller
public class AdminControllerImpl implements AdminController {

    @Override
    public String showAdminPage() {
        return "admin-page";
    }
}
