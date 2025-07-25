package io.github.felipevenas.api_livraria.services;

import io.github.felipevenas.api_livraria.model.entities.Author;
import io.github.felipevenas.api_livraria.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Optional<Author> getById(UUID id) {
        return authorRepository.findById(id);
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public void deleteAuthor(UUID id) {
        authorRepository.deleteById(id);
    }

}
