package br.com.ifpb.ads.bookifyapi.controller;

import br.com.ifpb.ads.bookifyapi.dto.AuthorCreateDTO;
import br.com.ifpb.ads.bookifyapi.dto.AuthorDTO;
import br.com.ifpb.ads.bookifyapi.exception.RegraDeNegocioException;
import br.com.ifpb.ads.bookifyapi.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/autor")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/{id}")
    public AuthorDTO findById(@PathVariable Integer id) throws Exception {
        return authorService.findById(id);
    }

    @GetMapping
    public ResponseEntity<Page<AuthorDTO>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortDirection) {
        Page<AuthorDTO> authors = authorService.findAll(page, size, sortField, sortDirection);
        return ResponseEntity.ok(authors);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AuthorDTO>> listWithoutPaging(){
        return ResponseEntity.ok(authorService.findAll());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarAutor(@PathVariable Integer id) {
        try {
            authorService.deleteAuthor(id);
            return ResponseEntity.ok("Autor deletado com sucesso.");
        } catch (RegraDeNegocioException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorCreateDTO authorCreateDTO) {
        AuthorDTO newAuthor = authorService.createAuthor(authorCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAuthor);
    }


}
