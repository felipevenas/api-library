package io.github.felipevenas.api_livraria.controllers.mappers;

import io.github.felipevenas.api_livraria.dto.AuthorDto;
import io.github.felipevenas.api_livraria.model.entities.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    Author toAuthor(AuthorDto authorDto);

    AuthorDto toAuthorDto(Author author);

}
