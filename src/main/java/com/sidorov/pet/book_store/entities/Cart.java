package com.sidorov.pet.book_store.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "cart", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "book_id"})})
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id", nullable = false)
    Book book;

    @Min(value = 1)
    @Column(name = "count_books", nullable = false)
    Integer countBooks;
}
