package io.github.felipevenas.api_livraria.services;

import io.github.felipevenas.api_livraria.model.entities.Author;
import io.github.felipevenas.api_livraria.repositories.AuthorRepository;
import io.github.felipevenas.api_livraria.validator.AuthorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;
    private final AuthorValidator authorValidator;

    // Injeção de dependência.
    public AuthorService(AuthorValidator authorValidator, AuthorRepository authorRepository) {
        this.authorValidator = authorValidator;
        this.authorRepository = authorRepository;
    }

    // Sempre que o 'save' for chamado, ocorrerá uma validação.
    public void save(Author author) {
        authorValidator.validator(author);
        authorRepository.save(author);
    }

    public Optional<Author> findById(UUID id) {
        return authorRepository.findById(id);
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public void delete(Author author) {
        authorRepository.delete(author);
    }

    public void update(Author author) {
        if (author.getId() == null) {
            throw new IllegalArgumentException("This author isn't saved in database!");
        }
        authorRepository.save(author);
    }

    public List<Author> findBy(String name, String nationality) {
        if (name != null && nationality != null) {
            return authorRepository.findByNameOrNationality(name, nationality);
        } else if (name != null) {
            return authorRepository.findByName(name);
        }
        else if (nationality != null){
            return authorRepository.findByNationality(nationality);
        }
        else {
            return findAll();
        }
    }

}
