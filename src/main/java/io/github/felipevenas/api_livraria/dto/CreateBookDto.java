package io.github.felipevenas.api_livraria.dto;

import io.github.felipevenas.api_livraria.model.entities.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.ISBN;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreateBookDto(@NotBlank(message = "Required field.")
                            @ISBN
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

}
