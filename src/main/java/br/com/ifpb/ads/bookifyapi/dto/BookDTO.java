package br.com.ifpb.ads.bookifyapi.dto;

import br.com.ifpb.ads.bookifyapi.entity.Author;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookDTO {

    private long id;

    @NotNull
    @NotEmpty
    private String title;

    private int quantity_stock;

    private double price;

    private List<Author> autores;

}
