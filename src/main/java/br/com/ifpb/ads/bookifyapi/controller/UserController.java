package br.com.ifpb.ads.bookifyapi.controller;


import br.com.ifpb.ads.bookifyapi.dto.UserDTO;
import br.com.ifpb.ads.bookifyapi.entity.User;
import br.com.ifpb.ads.bookifyapi.exception.RegraDeNegocioException;
import br.com.ifpb.ads.bookifyapi.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    private final UserService userService;


    @Autowired
    private ObjectMapper objectMapper;


    public UserController(UserService userService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
    }


    @GetMapping
    public List<UserDTO> listarUsuarios() {
        return userService.listarTodos();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Integer id) {
        userService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/tornar-admin")
    public ResponseEntity<?> tornarAdmin(@PathVariable Integer id) {
        try {
            User user = userService.tornarAdmin(id);
            UserDTO userDTO = objectMapper.convertValue(user, UserDTO.class);
            return ResponseEntity.ok(userDTO);
        } catch (RegraDeNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // Retorna erro 400 com a mensagem da exceção
        }
    }


}