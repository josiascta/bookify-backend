package br.com.ifpb.ads.bookifyapi.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

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
    private long id;

    @Column(name = "titulo")
    private String title;

    @Column(name = "quantidade_estoque")
    private int quantity_stock;

    @Column(name = "preco")
    private double price;
}
