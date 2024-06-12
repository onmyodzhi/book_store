package com.sidorov.pet.book_store.entities.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartDTO {
    Long id;
    UserDTO user;
    BookDTO book;
    Integer countBooks;
}
