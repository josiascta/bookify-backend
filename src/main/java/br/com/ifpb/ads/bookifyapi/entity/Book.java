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
@Entity(name = "LIVRO")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LIVRO")
    @SequenceGenerator(name = "SEQ_LIVRO", sequenceName = "SEQ_LIVRO", allocationSize = 1)
    @Column(name = "id_livro")
    private Integer id;

    @Column(name = "titulo")
    private String title;

    @Column(name = "quantidade_estoque")
    private int quantity_stock;

    @Column(name = "preco")
    private double price;

    @ManyToMany
    @JoinTable(
            name = "livro_autor",
            joinColumns = @JoinColumn(name = "id_livro"),
            inverseJoinColumns = @JoinColumn(name = "id_autor")
    )
    @JsonIgnoreProperties("livros")
    private List<Author> autores;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria")
    private Category category;
}
