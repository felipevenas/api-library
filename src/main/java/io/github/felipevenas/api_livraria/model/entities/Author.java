package io.github.felipevenas.api_livraria.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_author", schema = "public")
@Getter
@Setter
@ToString (exclude = "books") // É preciso excluir o parâmetro 'Lazy' do toString para não ser iniciado antes da hora, causando uma LazyException.
@EntityListeners(AuditingEntityListener.class) // Se nesse código houver anotações @LastModifiedDate ou @CreateDate, o código fica a escutar em segundo plano para realizar atualizações.
public class Author {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "date_birthday", nullable = false)
    @Past(message = "Past date is required.")
    private LocalDate dateBirthday;

    @Column(name = "nationality", length = 50, nullable = false)
    private String nationality;

    @Column(name = "signup_date")
    @CreatedDate
    private LocalDateTime signupDate;

    @Column(name = "last_update_date")
    @LastModifiedDate
    private LocalDateTime lastUpdateDate;

    @JsonBackReference
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY) // Parâmetro do tipo 'Lazy'
    private List<Book> books;

}
