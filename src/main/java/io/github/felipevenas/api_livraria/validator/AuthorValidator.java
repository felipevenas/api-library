package io.github.felipevenas.api_livraria.validator;

import io.github.felipevenas.api_livraria.exceptions.ContainBooksException;
import io.github.felipevenas.api_livraria.exceptions.DuplicatedRegistryException;
import io.github.felipevenas.api_livraria.model.entities.Author;
import io.github.felipevenas.api_livraria.repositories.AuthorRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthorValidator {

    private AuthorRepository authorRepository;

    // Injeção de dependência.
    public AuthorValidator(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void duplicityValidator(Author author) {
        Optional<Author> possibleAuthor = authorRepository.findByNameAndDateBirthdayAndNationality(
                author.getName(),
                author.getDateBirthday(),
                author.getNationality());

        if (possibleAuthor.isPresent()) {
            throw new DuplicatedRegistryException("This author already exists in database!");
        }
    }

}
