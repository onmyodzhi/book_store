package com.sidorov.pet.book_store.controllers.ui.interfaces;

import org.springframework.web.bind.annotation.GetMapping;

public interface AdminController {

    @GetMapping("/admin")
    String showAdminPage();
}
