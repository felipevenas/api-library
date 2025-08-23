package io.github.felipevenas.api_livraria.controllers;

import io.github.felipevenas.api_livraria.controllers.mappers.BookMapper;
import io.github.felipevenas.api_livraria.dto.CreateBookDto;
import io.github.felipevenas.api_livraria.dto.SearchBookDto;
import io.github.felipevenas.api_livraria.model.entities.Book;
import io.github.felipevenas.api_livraria.model.entities.Genre;
import io.github.felipevenas.api_livraria.services.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController implements GenericController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid CreateBookDto createBookDto) {
        Book book = bookMapper.toBook(createBookDto);
        bookService.save(book);
        var url = generateHeaderLocation(book.getId());
        return ResponseEntity.created(url).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<SearchBookDto> findById(
            @PathVariable("id") String id) {

        return bookService.findById(UUID.fromString(id))
                .map(book -> {
                    var dto = bookMapper.toDto(book);
                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping
    public ResponseEntity<Object> deleteByObject(
            @PathVariable String id) {

        return bookService.findById(UUID.fromString(id))
                .map(book -> {
                    bookService.delete(book);
                    return ResponseEntity.noContent().build();
                }).orElseGet( () -> ResponseEntity.notFound().build());

    }

    @GetMapping()
    public ResponseEntity<List<SearchBookDto>> search(
            @RequestParam(value = "isbn", required = false)
            String isbn,
            @RequestParam(value = "title", required = false)
            String title,
            @RequestParam(value = "author-name", required = false)
            String authorName,
            @RequestParam(value = "genre", required = false)
            Genre genre,
            @RequestParam(value = "publication-year", required = false)
            Integer publicationYear) {

        var result = bookService.search(isbn, title, authorName, genre, publicationYear);
        var list = result
                .stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(list);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable("id") String id,
                                       @RequestBody @Valid CreateBookDto dto) {

        return bookService.findById(UUID.fromString(id))
                .map(book -> {
                    Book entityAux = bookMapper.toBook(dto);

                    book.setTitle(entityAux.getTitle());
                    book.setIsbn(entityAux.getIsbn());
                    book.setPrice(entityAux.getPrice());
                    book.setGenre(entityAux.getGenre());
                    book.setPublicationDate(entityAux.getPublicationDate());

                    bookService.save(book);

                    return ResponseEntity.noContent().build();

                }).orElseGet( () -> ResponseEntity.notFound().build() );

    }

}

