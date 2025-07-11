package io.github.felipevenas.api_livraria.repositories;

import io.github.felipevenas.api_livraria.model.entities.Author;
import io.github.felipevenas.api_livraria.model.entities.Book;
import io.github.felipevenas.api_livraria.model.entities.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Test
    void saveTest() {
        Book book = new Book();
        book.setIsbn("10255-90000");
        book.setPrice(BigDecimal.valueOf(100));
        book.setTitle("Mental's Flashes");
        book.setGenre(Genre.MISTERIO);
        book.setPublicationDate(LocalDate.of(2015, 5, 20));

        Author author = authorRepository
                .findById(UUID.fromString("150a4f0d-5105-43a6-8be6-18e640fa036e"))
                .orElse(null);
        book.setAuthor(author);

        bookRepository.save(book);
    }

    @Test
    void saveCascadeTest() {
        Author author = new Author();
        author.setName("João Pietro");
        author.setNationality("Italian");
        author.setDateBirthday(LocalDate.of(1984, 1, 21));

        Book book = new Book();
        book.setIsbn("10255-90123");
        book.setPrice(BigDecimal.valueOf(40));
        book.setTitle("Biography of Pietro");
        book.setGenre(Genre.BIOGRAFIA);
        book.setPublicationDate(LocalDate.of(2015, 5, 20));
        book.setAuthor(author);

        bookRepository.save(book);
    }

    @Test
    void updateTest() {
        var id = UUID.fromString("");
        Optional<Book> possibleBook = bookRepository.findById(id);

        if (possibleBook.isPresent()) {
            Book book = possibleBook.get();
            book.setTitle("");
            book.setPublicationDate(LocalDate.of(2018, 05, 25));
            book.setPrice(BigDecimal.valueOf(80));
            book.setGenre(Genre.FICCAO);

            bookRepository.save(book);
        }

    }

}