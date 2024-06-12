package com.sidorov.pet.book_store.controllers.ui.implementations;

import com.sidorov.pet.book_store.controllers.ui.interfaces.AboutController;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/about")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AboutControllerImpl implements AboutController {

    @Override
    public String showHomePage() {
        return "about-page";
    }
}
