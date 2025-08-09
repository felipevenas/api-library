package io.github.felipevenas.api_livraria.services;

import io.github.felipevenas.api_livraria.model.entities.Book;
import io.github.felipevenas.api_livraria.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public void save(Book book){
        bookRepository.save(book);
    }

}
