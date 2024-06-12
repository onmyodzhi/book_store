package com.sidorov.pet.book_store.controllers.rest.implementations;

import com.sidorov.pet.book_store.controllers.rest.interfaces.BookRestController;
import com.sidorov.pet.book_store.entities.dto.BookDTO;
import com.sidorov.pet.book_store.exceptions.ResourceNotFoundException;
import com.sidorov.pet.book_store.services.BookService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@AllArgsConstructor(onConstructor_ = {@Autowired})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookRestControllerImpl implements BookRestController {
    BookService bookService;

    @Override
    public List<BookDTO> showAllBookDTOs() {
        return bookService.getAllBook();
    }

    @Override
    public BookDTO showBook(@PathVariable Long id) {
        return bookService.findBookById(id);
    }

    @Override
    public BookDTO createBook(@RequestBody BookDTO bookDTO) {
        if (bookDTO.getId() != null)
            bookDTO.setId(null);
        return bookService.saveOrUpdateBook(bookDTO);
    }

    @Override
    public BookDTO updateBook(@RequestBody BookDTO bookDTO) {
        if (bookService.existsBookById(bookDTO.getId())) {
            return bookService.saveOrUpdateBook(bookDTO);
        } else throw new ResourceNotFoundException("Book with id: " + bookDTO.getId() + " not found");
    }

    @Override
    public void deleteBook(@PathVariable Long id) {
        if (bookService.existsBookById(id)) {
            bookService.deleteBookById(id);
        } else throw new ResourceNotFoundException("Book with id: " + id + " not found");
    }

    @Override
    public void deleteAllBooks() {
        bookService.deleteAllBooks();
    }
}
