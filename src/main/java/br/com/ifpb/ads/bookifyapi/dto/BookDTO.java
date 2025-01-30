package br.com.ifpb.ads.bookifyapi.dto;

import br.com.ifpb.ads.bookifyapi.entity.Author;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Long id;
    private String title;
    private Double price;
    private Integer quantity_stock;
    private List<AuthorDTO> autores;
}
