package io.github.felipevenas.api_livraria.repositories;

import io.github.felipevenas.api_livraria.model.entities.Author;
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

    @Test
    public void saveTest() {
       Author author = new Author();
       author.setName("Philipe Corrêa");
       author.setNationality("Spanish");
       author.setDateBirthday(LocalDate.of(2001, 3, 30));

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
    public void listTest() {
        List<Author> list = authorRepository.findAll();
        list.forEach(System.out::println);
    }

    @Test
    public void deleteByIdTest() {
        var id = UUID.fromString("8691ab76-c4de-4935-8243-ee19609b4f16");

        Optional<Author> possibleAuthor = authorRepository.findById(id);
        Author author = possibleAuthor.get();
        System.out.println("Author deleted: " + author);
        authorRepository.deleteById(id);
    }


    @Test
    public void countTest() {
        System.out.println("Quantity of authors: " + authorRepository.count());
    }
}
