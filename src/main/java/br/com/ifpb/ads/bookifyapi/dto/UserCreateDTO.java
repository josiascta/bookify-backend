package br.com.ifpb.ads.bookifyapi.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCreateDTO {

    @NotEmpty
    private String login;

    @NotEmpty
    private String senha;

    @NotEmpty
    private List<Integer> cargos;
}
