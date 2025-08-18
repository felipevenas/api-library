package io.github.felipevenas.api_livraria.dto;

import io.github.felipevenas.api_livraria.model.entities.Author;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

// Data Transfer Object = Classe de transferência de dados.
public record AuthorDto(
        UUID id,
        @NotBlank(message = "Required field.")
        String name,
        @NotNull(message = "Required field.")
        LocalDate dateBirthday,
        @NotBlank(message = "Required field.")
        String nationality) {

}
