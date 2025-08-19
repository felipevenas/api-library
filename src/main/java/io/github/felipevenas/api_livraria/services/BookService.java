package io.github.felipevenas.api_livraria.services;

import io.github.felipevenas.api_livraria.model.entities.Book;
import io.github.felipevenas.api_livraria.model.entities.Genre;
import io.github.felipevenas.api_livraria.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

// Importando os métodos estáticos de BookSpec:
import static io.github.felipevenas.api_livraria.repositories.specifications.BookSpec.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public void save(Book book) {
        bookRepository.save(book);
    }

    public Optional<Book> findById(UUID id) {
        return bookRepository.findById(id);
    }

    public void delete(Book book) {
        bookRepository.delete(book);
    }

    public List<Book> search(
            String isbn, String title, String authorName, Genre genre, Integer publicationYear) {

        Specification<Book> specification = Specification.where(
                (root, query, criteriaBuilder) ->
                        criteriaBuilder.conjunction());

        if (isbn != null) {
            specification.and(isbnEqual(isbn));
        }

        if (title != null) {
            specification.and(titleLike(title));
        }

        if (genre != null) {
            specification.and(genreEqual(genre));
        }

        return bookRepository.findAll();

    }

}
