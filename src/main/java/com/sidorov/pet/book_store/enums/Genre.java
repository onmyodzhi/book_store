package com.sidorov.pet.book_store.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Genre {
    FANTASY("FANTASY"),
    FICTION("FICTION"),
    DETECTIVE("DETECTIVE");

    private String genre;
}
