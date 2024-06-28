package com.sidorov.pet.book_store.exceptions;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookServiceError {
     int status;
     String message;
     Date timestamp;

     public BookServiceError(int status, String message) {
          this.status = status;
          this.message = message;
          this.timestamp = new Date();
     }
}