package io.github.felipevenas.api_livraria.repositories;

import io.github.felipevenas.api_livraria.model.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

// JpaRepository -> Agregado de outras interfaces que carregam métodos básicos para uma interação com o banco.
public interface AuthorRepository extends JpaRepository<Author, UUID> {

}
