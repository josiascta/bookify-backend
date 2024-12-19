package br.com.ifpb.ads.bookifyapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "AUTOR")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_AUTOR")
    @SequenceGenerator(name = "SEQ_AUTOR", sequenceName = "SEQ_AUTOR", allocationSize = 1)
    @Column(name = "id_autor")
    private Integer id;

    @Column(name = "nome")
    private String name;

    @ManyToMany(mappedBy = "autores")
    @JsonIgnoreProperties("autores")
    private List<Book> livros;
}
