package br.com.ifpb.ads.bookifyapi.controller;

import br.com.ifpb.ads.bookifyapi.dto.LoginDTO;
import br.com.ifpb.ads.bookifyapi.dto.UserCreateDTO;
import br.com.ifpb.ads.bookifyapi.dto.UserDTO;
import br.com.ifpb.ads.bookifyapi.entity.User;
import br.com.ifpb.ads.bookifyapi.exception.RegraDeNegocioException;
import br.com.ifpb.ads.bookifyapi.security.TokenService;
import br.com.ifpb.ads.bookifyapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final TokenService tokenService;

    @PostMapping("/register")
    public User cadastrarUser(@RequestBody UserCreateDTO user) throws RegraDeNegocioException {
        return userService.cadastrarUsuario(user);
    }

    @GetMapping
    public Optional<User> findByLoginAndSenha(@RequestParam String login,
                                              @RequestParam String senha){
        return userService.findByLoginAndSenha(login, senha);
    }

    @PostMapping("/token")
    public String createToken(@RequestBody LoginDTO loginDTO) throws AuthenticationException {
        return tokenService.gerarToken(loginDTO);
    }

    @GetMapping("/{id}")
    public Optional<UserDTO> findById(@PathVariable Integer id){
        return userService.findById(id);
    }
}
