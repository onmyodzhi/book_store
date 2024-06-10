package com.sidorov.pet.book_store.controllers;

import com.sidorov.pet.book_store.entities.Book;
import com.sidorov.pet.book_store.services.BookService;
import com.sidorov.pet.book_store.utils.BookFilter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/books")
@AllArgsConstructor(onConstructor_ = {@Autowired})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookController {

    BookService bookServices;

    @GetMapping
    public String showAllBooks(Model model,
                               @RequestParam(name = "p", defaultValue = "1") Integer p,
                               @RequestParam Map<String, String> params) {
        BookFilter bookFilter = new BookFilter(params);
        Page<Book> page = bookServices.getAllBooks(bookFilter, p - 1, 5, bookFilter.getSortOrder());

        int currentPage = p;
        int totalPages = page.getTotalPages();
        int startPage, endPage;

        if (totalPages <= 4) {
            startPage = 1;
            endPage = totalPages;
        } else if (currentPage <= 2) {
            startPage = 1;
            endPage = 4;
        } else if (currentPage >= totalPages - 1) {
            startPage = totalPages - 3;
            endPage = totalPages;
        } else {
            startPage = currentPage - 1;
            endPage = currentPage + 2;
        }

        model.addAttribute("books", page.getContent());
        model.addAttribute("currentPage", p);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("params", params);
        return "store-page";
    }

    @GetMapping("/rest")
    @ResponseBody
    @CrossOrigin("*")
    public List<Book> showAllBooks() {
        return bookServices.getAllBooks();
    }
}
