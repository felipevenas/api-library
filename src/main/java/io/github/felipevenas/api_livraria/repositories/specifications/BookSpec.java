package io.github.felipevenas.api_livraria.repositories.specifications;

import io.github.felipevenas.api_livraria.model.entities.Book;
import io.github.felipevenas.api_livraria.model.entities.Genre;
import org.springframework.data.jpa.domain.Specification;

public class BookSpec {

    public static Specification<Book> isbnEqual(String isbn) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isbn"), isbn);
    }

    public static Specification<Book> titleLike(String title) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(criteriaBuilder.upper(root.get("title")), "%" + title.toUpperCase() + "%");
    }

    public static Specification<Book> genreEqual(Genre genre) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("genre"), genre);
    }

    public static Specification<Book> publicationYearEqual(Integer publicationYear) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(criteriaBuilder.function(
                        "to_char",
                        String.class,
                        root.get("publicationYear"),
                        criteriaBuilder.literal("YYYY")),
                        publicationYear.toString());
    }


}
