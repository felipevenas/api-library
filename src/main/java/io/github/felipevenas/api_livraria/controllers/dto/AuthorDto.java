package io.github.felipevenas.api_livraria.controllers.dto;

import io.github.felipevenas.api_livraria.model.entities.Author;

import java.time.LocalDate;

// Data Transfer Object = Classe de transferência de dados.
public record AuthorDto(String name,
                        LocalDate dateBirthday,
                        String nationality) {

    public Author convertToAuthor() {
        Author author = new Author();
        author.setName(name);
        author.setDateBirthday(dateBirthday);
        author.setNationality(nationality);
        return author;
    }

}
