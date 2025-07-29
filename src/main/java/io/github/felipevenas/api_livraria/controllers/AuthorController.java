package io.github.felipevenas.api_livraria.controllers;

import io.github.felipevenas.api_livraria.controllers.dto.AuthorDto;
import io.github.felipevenas.api_livraria.model.entities.Author;
import io.github.felipevenas.api_livraria.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody AuthorDto author){

        var authorEntity = author.convertToAuthor();
        authorService.save(authorEntity);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(authorEntity.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<AuthorDto> findById(@PathVariable("id") String id) {

        var idAuthor = UUID.fromString(id);
        Optional<Author> possibleAuthor = authorService.findById(idAuthor);

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
    public ResponseEntity<Void> delete(@PathVariable String id) {

        var idAuthor = UUID.fromString(id);
        Optional<Author> possibleAuthor = authorService.findById(idAuthor);

        if(possibleAuthor.isPresent()) {
            authorService.delete(possibleAuthor.get());
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<AuthorDto>> filterBy(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "nationality", required = false) String nationality) {

        List<Author> result = authorService.findBy(name, nationality);
        List<AuthorDto> authors = result.stream().map(author -> new AuthorDto(
                author.getId(),
                author.getName(),
                author.getDateBirthday(),
                author.getNationality())).toList();
        return ResponseEntity.ok(authors);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(
            @PathVariable("id") String id,
            @RequestBody AuthorDto authorDto) {

        UUID idAuthor = UUID.fromString(id);
        Optional<Author> possibleAuthor = authorService.findById(idAuthor);

        if(possibleAuthor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var author = possibleAuthor.get();
        author.setName(authorDto.name());
        author.setNationality(authorDto.nationality());
        author.setDateBirthday(authorDto.dateBirthday());
        authorService.save(author);
        return ResponseEntity.ok().build();
    }
}
