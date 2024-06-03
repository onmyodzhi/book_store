package com.sidorov.pet.book_store.utils;

import com.sidorov.pet.book_store.entities.Book;
import com.sidorov.pet.book_store.enums.Genre;
import com.sidorov.pet.book_store.repositories.specifications.BookSpecifications;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class BookFilter {

    Specification<Book> spec;
    String sortOrder = "asc";

    public BookFilter(Map<String,String> params) {
        spec = Specification.where(null);
        if (params.containsKey("maxPrice") && !params.get("maxPrice").isEmpty()) {
            spec = spec.and(BookSpecifications.priceLessOrEqualsThan(Integer.parseInt(params.get("maxPrice"))));
        }
        if (params.containsKey("minPrice") && !params.get("minPrice").isEmpty()) {
            spec = spec.and(BookSpecifications.priceGreaterOrEqualsThan(Integer.parseInt(params.get("minPrice"))));
        }
        if (params.containsKey("partOfTitle") && !params.get("partOfTitle").isEmpty()) {
            spec = spec.and(BookSpecifications.titleLike(params.get("partOfTitle")));
        }
        if (params.containsKey("sortOrder") && !params.get("sortOrder").isEmpty()) {
            sortOrder = params.get("sortOrder");
        }
        if (params.containsKey("genre") && !params.get("genre").isEmpty()) {
            String[] genres = params.get("genre").split(",");
            Specification<Book> genreSpec = Specification.where(null);
            for (String genre : genres) {
                genreSpec = genreSpec.or(BookSpecifications.genreEquals(Genre.valueOf(genre)));
            }
            spec = spec.and(genreSpec);
        }

    }
}
