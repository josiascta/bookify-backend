package br.com.ifpb.ads.bookifyapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "CATEGORIA")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CATEGORIA")
    @SequenceGenerator(name = "SEQ_CATEGORIA", sequenceName = "SEQ_CATEGORIA", allocationSize = 1)
    @Column(name = "id_categoria")
    private long id;

    @Column(name = "name")
    private String name;


}
