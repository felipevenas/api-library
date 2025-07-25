package io.github.felipevenas.api_livraria.controllers;

import io.github.felipevenas.api_livraria.controllers.dto.AuthorDto;
import io.github.felipevenas.api_livraria.model.entities.Author;
import io.github.felipevenas.api_livraria.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping
    public ResponseEntity<Void> saveAuthor(@RequestBody AuthorDto author){

        var authorEntity = author.convertToAuthor();
        authorService.saveAuthor(authorEntity);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(authorEntity.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<AuthorDto> getById(@PathVariable("id") String id) {

        var idAuthor = UUID.fromString(id);
        Optional<Author> possibleAuthor = authorService.getById(idAuthor);

        if(possibleAuthor.isPresent()) {
            Author author = possibleAuthor.get();
            AuthorDto authorDto = new AuthorDto(author.getId(),
                                                author.getName(),
                                                author.getDateBirthday(),
                                                author.getNationality());
            return ResponseEntity.ok(authorDto);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable String id) {

        var idAuthor = UUID.fromString(id);
        Optional<Author> possibleAuthor = authorService.getById(idAuthor);

        if(possibleAuthor.isPresent()) {
            Author author = possibleAuthor.get();
            authorService.deleteAuthor(author.getId());
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

}
