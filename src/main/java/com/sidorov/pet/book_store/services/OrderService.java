package com.sidorov.pet.book_store.services;

import com.sidorov.pet.book_store.entities.Book;
import com.sidorov.pet.book_store.entities.Cart;
import com.sidorov.pet.book_store.entities.Order;
import com.sidorov.pet.book_store.entities.User;
import com.sidorov.pet.book_store.repositories.OrderRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderService {

    OrderRepository orderRepository;
    CartService cartService;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> findAllByUserId(Long id) {
        return orderRepository.findAllByUserId(id);
    }

    public void save(User user, Book book, Integer count) {
        Order order = new Order();

        order.setUser(user);
        order.setBook(book);
        order.setCountBooks(count);
        order.setPrice(book.getPrice() * count);

        orderRepository.save(order);
    }

    @Transactional
    public void saveAllFromCart(List<Cart> cart) {
        List<Order> orders = new ArrayList<>();

        User user;
        Book book;
        int bookCount = 0;
        for (Cart value : cart) {
            Order order = new Order();

            user = value.getUser();
            book = value.getBook();
            bookCount = value.getCountBooks();

            order.setUser(user);
            order.setBook(book);
            order.setCountBooks(bookCount);
            order.setPrice(book.getPrice() * bookCount);

            orders.add(order);
        }

        orderRepository.saveAll(orders);
        cartService.deleteAll(cart);
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public Order saveOrUpdate(Order order) {
        return orderRepository.save(order);
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}
