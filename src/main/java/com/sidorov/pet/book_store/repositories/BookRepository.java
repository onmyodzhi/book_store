package com.sidorov.pet.book_store.repositories;

import com.sidorov.pet.book_store.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    @Query("select c.book from Cart c where c.user.id = :userId")
    Book findAllBooksByUserId(@Param("userId") Long userId);
}
