package com.sidorov.pet.book_store.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum Genre {
    FANTASY("FANTASY"),
    FICTION("FICTION"),
    DETECTIVE("DETECTIVE");

    final String genre;
}
