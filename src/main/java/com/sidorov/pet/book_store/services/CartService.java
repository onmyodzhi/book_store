package com.sidorov.pet.book_store.services;

import com.sidorov.pet.book_store.entities.Book;
import com.sidorov.pet.book_store.entities.Cart;
import com.sidorov.pet.book_store.entities.User;
import com.sidorov.pet.book_store.repositories.CartRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartService {
    CartRepository cartRepository;
    BookServices bookServices;
    UserService userService;

    public List<Cart> getCart(Long id) {
        return cartRepository.findCartByUserId(id).orElse(null);
    }

    public void saveOrUpdate(String username, Long bookId, int countBook) {
        Cart cart = cartRepository.findCartByUserUsernameAndBookId(username, bookId);
        if (cart == null) {
            cart = cartFilling(username, bookId);
        }else
            cart.setCountBooks(countBook);
        saveOrUpdate(cart);
    }

    public void addToCart(String username, Long bookId) {
        Cart cart = cartRepository.findCartByUserUsernameAndBookId(username, bookId);
        if (cart == null) {
            cart = cartFilling(username, bookId);
            saveOrUpdate(cart);
            return;
        }
        int booksQuantity = cart.getCountBooks();
        cart.setCountBooks(++booksQuantity);
        cartRepository.save(cart);
    }

    public void delete(String username, Long bookId) {
        Cart cart = cartRepository.findCartByUserUsernameAndBookId(username, bookId);
        if (cart != null) {
            cartRepository.delete(cart);
        }
    }

    private void saveOrUpdate(Cart cart) {
        cartRepository.save(cart);
    }

    private Cart cartFilling(String username, Long bookId) {
        User user = userService.getUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Book book = bookServices.findBookById(bookId);
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setBook(book);
        cart.setCountBooks(1);
        return cart;
    }
}
