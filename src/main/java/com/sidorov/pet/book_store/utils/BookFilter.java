package com.sidorov.pet.book_store.utils;

import com.sidorov.pet.book_store.entities.Book;
import com.sidorov.pet.book_store.repositories.specifications.BookSpecifications;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class BookFilter {

    Specification<Book> spec;
    final String filterParams;

    public BookFilter(Map<String, String> params) {
        spec = Specification.where(null);
        StringBuilder paramsBuilder = new StringBuilder();

        String maxPriceParam = params.get("maxPrice");
        if (maxPriceParam != null && !maxPriceParam.isEmpty()) {
            int maxPrice = Integer.parseInt(maxPriceParam);
            spec = spec.and(BookSpecifications.priceLessOrEqualsThan(maxPrice));
            paramsBuilder.append("&maxPrice=").append(maxPrice);
        }

        String minPriceParam = params.get("minPrice");
        if (minPriceParam != null && !minPriceParam.isEmpty()) {
            int minPrice = Integer.parseInt(minPriceParam);
            spec = spec.and(BookSpecifications.priceGreaterOrEqualsThan(minPrice));
            paramsBuilder.append("&minPrice=").append(minPrice);
        }

        String partOfTitleParam = params.get("partOfTitle");
        if (partOfTitleParam != null && !partOfTitleParam.isEmpty()) {
            spec = spec.and(BookSpecifications.titleLike(partOfTitleParam));
            paramsBuilder.append("&partOfTitle=").append(partOfTitleParam);
        }

        String sortOrderParam = params.get("sortOrder");
        if (sortOrderParam != null && !sortOrderParam.isEmpty()) {
            paramsBuilder.append("&sortOrder=").append(sortOrderParam);
        }

        String genresParam = params.get("genres");
        if (genresParam != null && !genresParam.isEmpty()) {
            List<String> genres = Arrays.asList(genresParam.split(","));
            spec = spec.and(BookSpecifications.genresIn(genres));
            paramsBuilder.append("&genres=").append(String.join(",", genres));
        }

        filterParams = paramsBuilder.toString();
    }
}