package io.github.felipevenas.api_livraria.repositories;

import io.github.felipevenas.api_livraria.model.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

    List<Author> findAuthorsByName(String name);

    @Query("select a from Author as a order by a.dateBirthday")
    List<Author> listAll();

}
