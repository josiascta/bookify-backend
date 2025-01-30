package br.com.ifpb.ads.bookifyapi.dto;

import br.com.ifpb.ads.bookifyapi.entity.Author;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookCreateDTO {
    private Long id;  // Adicionei o id aqui
    private String title;
    private Double price;
    private Integer quantity_stock;
    private List<Long> autores_ids;

    public List<Long> getAutores() {
        return autores_ids;
    }

}