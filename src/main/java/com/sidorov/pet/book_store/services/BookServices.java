package com.sidorov.pet.book_store.services;

import com.sidorov.pet.book_store.entities.Book;
import com.sidorov.pet.book_store.exceptions.ResourceNotFoundException;
import com.sidorov.pet.book_store.repositories.BookRepository;
import com.sidorov.pet.book_store.utils.BookFilter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
@FieldDefaults(level = AccessLevel.PRIVATE)
@Transactional(readOnly = true)
public class BookServices {

    BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Page<Book> getAllBooks(BookFilter bookFilter, int page, int size, String sortOrder) {
        Specification<Book> spec = bookFilter.getSpec();
        Sort sort = Sort.by("price");
        if (sortOrder.equals("desc")) {
            sort = sort.descending();
        }else{
            sort = sort.ascending();
        }

        return bookRepository.findAll(spec, PageRequest.of(page, size, sort));
    }

    public Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with this id:" + id + " not found"));
    }

    @Transactional
    public Book saveOrUpdateBook(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}
