package io.github.felipevenas.api_livraria.dto;

import io.github.felipevenas.api_livraria.model.entities.Author;
import io.github.felipevenas.api_livraria.model.entities.Book;
import io.github.felipevenas.api_livraria.model.entities.Genre;
import io.github.felipevenas.api_livraria.repositories.AuthorRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public record BookDto(@NotBlank(message = "Required field.")
                      @Size(max = 13, min = 10)
                      String isbn,

                      @NotBlank(message = "Required field.")
                      @Size(max = 50, min = 1, message = "The field's size is not compatible.")
                      String title,

                      @PastOrPresent
                      @NotNull(message = "Required field.")
                      LocalDate publicationDate,

                      Genre genre,

                      @NotNull(message = "Required field.")
                      BigDecimal price,

                      @NotNull(message = "Required field.")
                      UUID idAuthor) {

    public Book convertToBook() {
            Book book = new Book();

            book.setIsbn(isbn);
            book.setTitle(title);
            book.setPublicationDate(publicationDate);
            book.setGenre(genre);
            book.setPrice(price);
            return book;

    }

}
