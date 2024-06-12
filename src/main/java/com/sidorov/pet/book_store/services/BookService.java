package com.sidorov.pet.book_store.services;

import com.sidorov.pet.book_store.entities.Book;
import com.sidorov.pet.book_store.entities.dto.BookDTO;
import com.sidorov.pet.book_store.entities.mappers.BookMapper;
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
public class BookService {

    BookRepository bookRepository;
    BookMapper bookMapper;

    public List<BookDTO> getAllBook() {
        return bookRepository.findAll().stream()
                .map(entity -> bookMapper.toDTO(entity)).toList();
    }

    public Page<BookDTO> getPageOfBooks(BookFilter bookFilter, int page, int size, String sortOrder) {
        Specification<Book> spec = bookFilter.getSpec();
        Sort sort = Sort.by("price");
        if (sortOrder.contains("&sortOrder=desc")) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }

        return bookRepository.findAll(spec, PageRequest.of(page, size, sort))
                .map(entity -> bookMapper.toDTO(entity));
    }

    public BookDTO findBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with this id:" + id + " not found"));
        return bookMapper.toDTO(book);
    }

    public boolean existsBookById(Long id) {
        return bookRepository.existsById(id);
    }

    public BookDTO findBookByUserId(Long id) {
        return bookMapper.toDTO(bookRepository.findAllBooksByUserId(id));
    }

    public void deleteAllBooks() {
        bookRepository.deleteAll();
    }

    @Transactional
    public BookDTO saveOrUpdateBook(BookDTO bookDTO) {
        Book book = bookMapper.toEntity(bookDTO);
        bookRepository.save(book);
        return bookDTO;
    }

    @Transactional
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}
