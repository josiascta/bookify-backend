package br.com.ifpb.ads.bookifyapi.dto;

import br.com.ifpb.ads.bookifyapi.entity.Role;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class UserDTO {

    private BigInteger idUsuario;

    private String nome;

    private String sobrenome;

    private String login;

    private List<String> cargos;
}
