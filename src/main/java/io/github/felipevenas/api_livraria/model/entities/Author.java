package io.github.felipevenas.api_livraria.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_author", schema = "public")
// @Data --> Abrevia a criação de todos os seguintes elementos:
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString (exclude = "books") // É preciso excluir o parâmetro 'Lazy' do toString
// para não ser iniciado antes da hora, causando uma LazyException.
@EqualsAndHashCode
public class Author {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "date_birthday", nullable = false)
    private LocalDate dateBirthday;

    @Column(name = "nationality", length = 50, nullable = false)
    private String nationality;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Parâmetro do tipo 'Lazy'
    //@Transient = Indica que o atributo não deve ser persistido em uma DB.
    private List<Book> books;

}
