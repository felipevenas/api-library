package io.github.felipevenas.api_livraria.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_book", schema = "public")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "isbn", length = 20, nullable = false)
    private String isbn;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @Enumerated(EnumType.STRING) // .STRING = Armazena o texto | .ORDINAL = Armazena a posição.
    @Column(name = "genre", length = 20, nullable = false)
    private Genre genre;

    @Column(name = "price", precision = 18, scale = 2, nullable = false)
    private BigDecimal price;

    @ManyToOne(cascade = CascadeType.ALL) // --> Qualquer operação feita em livro, ele fará na tabela de autor junto.
    @JoinColumn(name = "id_author")
    private Author author;
}
