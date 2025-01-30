package br.com.ifpb.ads.bookifyapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "AUTOR")
public class Author {

    @Id
    @Column(name = "id_autor")
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @ManyToMany(mappedBy = "autores")
    @JsonIgnoreProperties("autores")
    private List<Book> livros;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

}