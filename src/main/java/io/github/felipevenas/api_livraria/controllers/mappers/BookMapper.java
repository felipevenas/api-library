package io.github.felipevenas.api_livraria.controllers.mappers;

import io.github.felipevenas.api_livraria.dto.CreateBookDto;
import io.github.felipevenas.api_livraria.dto.SearchBookDto;
import io.github.felipevenas.api_livraria.model.entities.Book;
import io.github.felipevenas.api_livraria.repositories.AuthorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = AuthorMapper.class)
public abstract class BookMapper {

    @Autowired
    AuthorRepository authorRepository;

    @Mapping(target = "author", expression = "java( authorRepository.findById(dto.idAuthor()).orElse(null) )")
    public abstract Book toBook(CreateBookDto dto);

    public abstract CreateBookDto toBookDto(Book book);

    public abstract SearchBookDto toDto(Book book);
}
