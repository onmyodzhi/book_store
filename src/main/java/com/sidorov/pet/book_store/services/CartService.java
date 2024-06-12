package com.sidorov.pet.book_store.services;

import com.sidorov.pet.book_store.entities.Book;
import com.sidorov.pet.book_store.entities.Cart;
import com.sidorov.pet.book_store.entities.User;
import com.sidorov.pet.book_store.entities.dto.CartDTO;
import com.sidorov.pet.book_store.entities.mappers.BookMapper;
import com.sidorov.pet.book_store.entities.mappers.CartMapper;
import com.sidorov.pet.book_store.entities.mappers.UserMapper;
import com.sidorov.pet.book_store.repositories.CartRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartService {
    private final UserMapper userMapper;
    CartRepository cartRepository;
    CartMapper cartMapper;
    BookService bookServices;
    BookMapper bookMapper;
    UserService userService;

    public List<CartDTO> getAllCartByUserId(Long id) {
        List<Cart> allItemInCart = cartRepository.findCartByUserId(id).orElse(null);
        if (allItemInCart != null) {
            return allItemInCart.stream().map(entity -> cartMapper.toDTO(entity)).toList();
        }
        return null;
    }

    public void saveOrUpdate(String username, Long bookId, int countBook) {
        Cart cart = cartRepository.findCartByUserUsernameAndBookId(username, bookId);
        if (cart == null) {
            cart = cartFilling(username, bookId);
        } else
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

    @Transactional
    public void deleteAll(List<CartDTO> cartDTOs) {
        List<Cart> cart = cartDTOs.stream().map(dto -> cartMapper.toEntity(dto)).toList();
        cartRepository.deleteAll(cart);
    }

    private void saveOrUpdate(Cart cart) {
        cartRepository.save(cart);
    }

    private Cart cartFilling(String username, Long bookId) {
        User user = userMapper.toEntity(userService.getUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found")));
        Book book = bookMapper.toEntity(bookServices.findBookById(bookId));
        Cart cart = new Cart();

        cart.setUser(user);
        cart.setBook(book);
        cart.setCountBooks(1);

        return cart;
    }
}
