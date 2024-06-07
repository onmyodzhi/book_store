package com.sidorov.pet.book_store.repositories;

import com.sidorov.pet.book_store.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findCartByUserUsernameAndBookId(String username,Long id);
    Optional<List<Cart>> findCartByUserId(Long id);
}
