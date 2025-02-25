package br.com.ifpb.ads.bookifyapi.dto;

import br.com.ifpb.ads.bookifyapi.entity.Category;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookCreateDTO {

    @NotNull
    @NotEmpty
    private String title;

    private int quantity_stock;

    private double price;

    private List<Integer> autores_ids;

    private Category category;
}
