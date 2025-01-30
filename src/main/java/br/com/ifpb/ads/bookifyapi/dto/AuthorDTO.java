package br.com.ifpb.ads.bookifyapi.dto;

import br.com.ifpb.ads.bookifyapi.entity.Book;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AuthorDTO {

    private Long id;

    private String nome;

    private List<Book> livros;
    public String getNome() {
        return nome;
    }
}
