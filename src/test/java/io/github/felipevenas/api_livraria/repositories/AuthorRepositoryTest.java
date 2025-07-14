package io.github.felipevenas.api_livraria.repositories;

import io.github.felipevenas.api_livraria.model.entities.Author;
import io.github.felipevenas.api_livraria.model.entities.Book;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;

    @Test
    public void saveTest() {
       Author author = new Author();
       author.setName("Gustavo Silva");
       author.setNationality("Brazilian");
       author.setDateBirthday(LocalDate.of(1989, 12, 2));

       var savedAuthor = authorRepository.save(author);
        System.out.println("Saved author: " + savedAuthor);
    }

    @Test
    public void updateTest() {

        var id = UUID.fromString("5f627732-484a-43ad-8968-767e46f858d3");
        Optional<Author> possibleAuthor = authorRepository.findById(id);

        if (possibleAuthor.isPresent()) {

            Author author = possibleAuthor.get();
            System.out.println("Author data: " + author);
            System.out.println(author);

            author.setDateBirthday(LocalDate.of(1945, 4, 25));

            authorRepository.save(author);
        }
    }

    @Test
    void findBooksByAuthor() {
        UUID id = UUID.fromString("88dab940-ca4d-4759-b047-9d339f944786");
        var author = authorRepository.findById(id).get();

        List<Book> books = bookRepository.findBooksByAuthor(author);
        author.setBooks(books);

        author.getBooks().forEach(System.out::println);
    }

}
