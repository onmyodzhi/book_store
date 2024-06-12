package com.sidorov.pet.book_store.entities.dto;

import com.sidorov.pet.book_store.enums.Genre;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookDTO {
    Long id;
    String title;
    String description;
    Integer yearOfPublication;
    Double price;
    Genre genre;
}

