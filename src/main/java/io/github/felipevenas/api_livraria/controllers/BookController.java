package io.github.felipevenas.api_livraria.controllers;

import io.github.felipevenas.api_livraria.dto.BookDto;
import io.github.felipevenas.api_livraria.dto.ErrorResponse;
import io.github.felipevenas.api_livraria.exceptions.DuplicatedRegistryException;
import io.github.felipevenas.api_livraria.services.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid BookDto book) {
        try {
            var bookEntity = book.convertToBook();
            bookService.save(bookEntity);

            return ResponseEntity.ok(book);

        } catch (DuplicatedRegistryException e) {
            var errorDto = ErrorResponse.conflictResponse(e.getMessage());
            return ResponseEntity.status(errorDto.status()).build();
        }
    }
}
