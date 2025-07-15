package io.github.felipevenas.api_livraria.repositories;

import io.github.felipevenas.api_livraria.model.entities.Author;
import io.github.felipevenas.api_livraria.model.entities.Book;
import io.github.felipevenas.api_livraria.model.entities.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Test
    void saveTest() {
        Book book = new Book();
        book.setTitle("Second Book");
        book.setGenre(Genre.BIOGRAFIA);
        book.setPrice(BigDecimal.valueOf(120));
        book.setIsbn("00000-00002");
        book.setPublicationDate(LocalDate.of(1990, 03, 30));

        UUID id = UUID.fromString("d04cb3c4-dd9b-43f4-9e63-6f712e1b1f2f");
        Optional<Author> possibleAuthor = authorRepository.findById(id);

        if (possibleAuthor.isPresent()) {
            Author author = possibleAuthor.get();
            book.setAuthor(author);
            bookRepository.save(book);
        }
    }

    @Test
    void findByIdTest() {
        UUID id = UUID.fromString("c5e4fb58-afa7-46d2-aa71-ce15fa987d09");
        Optional<Book> possibleBook = bookRepository.findById(id);

        if(possibleBook.isPresent()) {
            Book book = possibleBook.get();
            System.out.println(book);
        }
    }

    @Test
    void deleteTest() {
        UUID id = UUID.fromString("00b1548b-276d-4787-9d99-6bdaa2a32d23");
        Optional<Book> possibleBook = bookRepository.findById(id);

        if (possibleBook.isPresent()) {
            Book book = possibleBook.get();
            System.out.println("Book data: " + book);
            bookRepository.deleteById(id);
            System.out.println("This book was deleted!");
        }
    }

    @Test
    void findBooksByIsbn() {
        String isbn = "00000-00002";
        List<Book> books = bookRepository.findBooksByIsbn(isbn);
        books.forEach(System.out::println);
    }

    @Test
    void findBooksByTitle() {
        String title = "First Book";
        Optional<Book> possibleBook = bookRepository.findBooksByTitle(title);

        if (possibleBook.isPresent()) {
            Book book = possibleBook.get();
            System.out.println(book);
        }
    }

    @Test
    void findBooksByAuthorAndPrice() {
        Author author = authorRepository.findById(UUID.fromString("d04cb3c4-dd9b-43f4-9e63-6f712e1b1f2f")).get();
        List<Book> books = bookRepository.findBooksByAuthorAndPrice(author, BigDecimal.valueOf(100));
        books.forEach(System.out::println);
    }

}
