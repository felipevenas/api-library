package io.github.felipevenas.api_livraria.validator;

import io.github.felipevenas.api_livraria.dto.CreateBookDto;
import io.github.felipevenas.api_livraria.exceptions.DuplicatedRegistryException;
import io.github.felipevenas.api_livraria.model.entities.Book;
import io.github.felipevenas.api_livraria.repositories.BookRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Optional;

@Component
public class BookValidator {

    private BookRepository bookRepository;

    public BookValidator(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void isbnValidator(Book book) {
        Optional<Book> possibleBook = bookRepository.findBookByIsbn(book.getIsbn());
        if (possibleBook.isPresent()) {
            throw new DuplicatedRegistryException("ISBN already exists!");
        }
    }

    public void publicationDateValidator(Book book) {

    }
}
