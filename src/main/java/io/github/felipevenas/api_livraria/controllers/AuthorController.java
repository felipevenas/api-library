package io.github.felipevenas.api_livraria.controllers;

import io.github.felipevenas.api_livraria.controllers.mappers.AuthorMapper;
import io.github.felipevenas.api_livraria.dto.AuthorDto;
import io.github.felipevenas.api_livraria.model.entities.Author;
import io.github.felipevenas.api_livraria.services.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/author")
public class AuthorController implements GenericController {

    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    public AuthorController(AuthorService authorService, AuthorMapper authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid AuthorDto authorDto) {

        var authorEntity = authorMapper.toAuthor(authorDto);
        authorService.save(authorEntity);

        URI location = generateHeaderLocation(authorEntity.getId());
        return ResponseEntity.created(location).build();

    }

    @GetMapping("{id}")
    public ResponseEntity<AuthorDto> findById(@PathVariable("id") String id) {

        var idAuthor = UUID.fromString(id);
        Optional<Author> possibleAuthor = authorService.findById(idAuthor);

        if (possibleAuthor.isPresent()) {
            Author author = possibleAuthor.get();
            AuthorDto authorDto = authorMapper.toAuthorDto(author);
            return ResponseEntity.ok(authorDto);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {

        var idAuthor = UUID.fromString(id);
        Optional<Author> possibleAuthor = authorService.findById(idAuthor);

        if (possibleAuthor.isPresent()) {
            authorService.delete(possibleAuthor.get());
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<AuthorDto>> filterBy(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "nationality", required = false) String nationality) {

        List<Author> result = authorService.findByExample(name, nationality);
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
            @RequestBody @Valid AuthorDto authorDto) {

        UUID idAuthor = UUID.fromString(id);
        Optional<Author> possibleAuthor = authorService.findById(idAuthor);

        if (possibleAuthor.isEmpty()) {
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
