package com.sidorov.pet.book_store.repositories.specifications;

import com.sidorov.pet.book_store.entities.Book;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class BookSpecifications {
    public static Specification<Book> priceGreaterOrEqualsThan(int minPrice) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    public static Specification<Book> priceLessOrEqualsThan(int maxPrice) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    public static Specification<Book> titleLike(String partOfTitle) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", partOfTitle));
    }

    public static Specification<Book> genresIn(List<String> genres) {
        return (root, criteriaQuery, criteriaBuilder) -> root.get("genre").in(genres);
    }
}
