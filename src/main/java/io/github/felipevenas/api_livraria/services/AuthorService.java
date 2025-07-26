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

    public void save(Author author) {
        authorRepository.save(author);
    }

    public Optional<Author> getById(UUID id) {
        return authorRepository.findById(id);
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public void delete(Author author) {
        authorRepository.delete(author);
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
