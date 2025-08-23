package io.github.felipevenas.api_livraria.repositories;

import io.github.felipevenas.api_livraria.model.entities.Author;
import io.github.felipevenas.api_livraria.model.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    // Query Methods --> Métodos de consulta:
    List<Book> findBooksByAuthor (Author author);

    List<Book> findBooksByIsbn (String isbn);

    Optional<Book> findBookByIsbn (String isbn);

    Optional<Book> findBooksByTitle (String title);

    List<Book> findBooksByAuthorAndPrice(Author author, BigDecimal price);

    // JPQL --> Referencia as entidades e parâmetros:
    @Query("select b from Book as b order by b.publicationDate")
    List<Book> listAll();

}
