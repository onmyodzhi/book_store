package com.sidorov.pet.book_store.controllers.ui.interfaces;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface BookController {

    @GetMapping
    String showAllBooks(Model model,
                        @RequestParam(name = "p", defaultValue = "1") Integer p,
                        @RequestParam Map<String, String> params,
                        @RequestParam(name = "genres", required = false) List<String> genres);
}
