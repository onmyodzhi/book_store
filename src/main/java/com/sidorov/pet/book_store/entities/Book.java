package com.sidorov.pet.book_store.entities;


import com.sidorov.pet.book_store.enums.Genre;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "title")
    String title;

    @Column(name = "description")
    String description;

    @Column(name = "year_of_publication")
    Integer yearOfPublication;

    @Column(name = "price")
    Double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre")
    Genre genre;
}
