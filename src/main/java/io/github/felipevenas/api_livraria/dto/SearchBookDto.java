package io.github.felipevenas.api_livraria.dto;

import io.github.felipevenas.api_livraria.model.entities.Author;
import io.github.felipevenas.api_livraria.model.entities.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record SearchBookDto(UUID id,

                            @NotBlank(message = "Required field.")
                            @Size(max = 13, min = 10)
                            String isbn,

                            @NotBlank(message = "Required field.")
                            @Size(max = 50, min = 1, message = "The field's size is not compatible.")
                            String title,

                            @PastOrPresent
                            @NotNull(message = "Required field.")
                            LocalDate publicationDate,

                            Genre genre,

                            @NotBlank(message = "Required field.")
                            BigDecimal price,
                            AuthorDto author) {
}
