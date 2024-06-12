package com.sidorov.pet.book_store.controllers.rest.interfaces;

import com.sidorov.pet.book_store.entities.dto.BookDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BookRestController {

    @GetMapping
    List<BookDTO> showAllBookDTOs();

    @GetMapping("/{id}")
    BookDTO showBook(@PathVariable Long id);

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    BookDTO createBook(@RequestBody BookDTO bookDTO);

    @PutMapping(consumes = "application/json", produces = "application/json")
    BookDTO updateBook(@RequestBody BookDTO bookDTO);

    @DeleteMapping("/{id}")
    void deleteBook(@PathVariable Long id);

    @DeleteMapping
    void deleteAllBooks();
}
