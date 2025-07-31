package io.github.felipevenas.api_livraria.repositories;

import io.github.felipevenas.api_livraria.model.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

    @Query
    List<Author> findByName(String name);

    @Query
    List<Author> findByNationality(String nationality);

    @Query
    List<Author> findByNameOrNationality(String name, String nationality);

    @Query
    Optional<Author> findByNameAndDateBirthdayAndNationality(String name, LocalDate dateBirthday, String nationality);

    @Query("select a from Author as a order by a.dateBirthday")
    List<Author> listAll();

}
