package com.sidorov.pet.book_store.services;

import com.sidorov.pet.book_store.entities.Book;
import com.sidorov.pet.book_store.entities.Cart;
import com.sidorov.pet.book_store.entities.Order;
import com.sidorov.pet.book_store.entities.User;
import com.sidorov.pet.book_store.entities.dto.BookDTO;
import com.sidorov.pet.book_store.entities.dto.CartDTO;
import com.sidorov.pet.book_store.entities.dto.OrderDTO;
import com.sidorov.pet.book_store.entities.dto.UserDTO;
import com.sidorov.pet.book_store.entities.mappers.BookMapper;
import com.sidorov.pet.book_store.entities.mappers.CartMapper;
import com.sidorov.pet.book_store.entities.mappers.OrderMapper;
import com.sidorov.pet.book_store.entities.mappers.UserMapper;
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
    OrderMapper orderMapper;
    CartService cartService;
    CartMapper cartMapper;
    UserMapper userMapper;
    BookMapper bookMapper;

    public List<OrderDTO> findAll() {
        return orderRepository.findAll()
                .stream().map(entity -> orderMapper.toDTO(entity)).toList();
    }

    public List<OrderDTO> findAllByUserId(Long id) {
        return orderRepository.findAllByUserId(id)
                .stream().map(entity -> orderMapper.toDTO(entity)).toList();
    }

    public void save(UserDTO userDTO, BookDTO bookDTO, Integer count) {
        Order order = new Order();
        User user = userMapper.toEntity(userDTO);
        Book book = bookMapper.toEntity(bookDTO);

        order.setUser(user);
        order.setBook(book);
        order.setCountBooks(count);
        order.setPrice(book.getPrice() * count);

        orderRepository.save(order);
    }

    @Transactional
    public void saveAllFromCart(List<CartDTO> cartDTOs) {
        List<Order> orders = new ArrayList<>();

        User user;
        Book book;
        int bookCount = 0;
        List<Cart> cart = cartDTOs.stream().map(dto -> cartMapper.toEntity(dto)).toList();

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
        cartService.deleteAll(cart.stream().map(entity -> cartMapper.toDTO(entity)).toList());
    }

    public OrderDTO findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        return orderMapper.toDTO(order);
    }

    public OrderDTO saveOrUpdate(OrderDTO orderDTO) {
        Order order = orderMapper.toEntity(orderDTO);
        orderRepository.save(order);
        return orderMapper.toDTO(order);
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}
