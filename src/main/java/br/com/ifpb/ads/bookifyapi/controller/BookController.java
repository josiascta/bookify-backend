package br.com.ifpb.ads.bookifyapi.controller;

import br.com.ifpb.ads.bookifyapi.dto.AuthorDTO;
import br.com.ifpb.ads.bookifyapi.dto.BookCreateDTO;
import br.com.ifpb.ads.bookifyapi.dto.BookDTO;
import br.com.ifpb.ads.bookifyapi.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/livro")
public class BookController {

    private final BookService bookService;

    // Criar um livro
    @PostMapping
    public ResponseEntity<BookDTO> create(@Valid @RequestBody BookCreateDTO bookCreateDTO) throws Exception {
        return new ResponseEntity<>(bookService.create(bookCreateDTO), HttpStatus.CREATED);
    }

    // Buscar livro por id
    @GetMapping("/{id}")
    public BookDTO findById(@PathVariable Long id) throws Exception {
        return bookService.findById(id);
    }

    @RequestMapping(value = "/excluir/{id}", method = RequestMethod.POST)
    public String excluirLivro(@PathVariable Long id, @RequestParam("_method") String method) throws Exception {
        if ("delete".equals(method)) {
            bookService.delete(id);
        }
        return "redirect:/livros-view";
    }

    // Atualizar livro
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> update(@PathVariable Long id, @RequestBody BookCreateDTO bookCreateDTO) throws Exception {
        return new ResponseEntity<>(bookService.editar(bookCreateDTO, id), HttpStatus.OK);
    }

    // Buscar todos os livros com paginação
    @GetMapping
    public ResponseEntity<Page<BookDTO>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortDirection) {
        Page<BookDTO> books = bookService.findAll(page, size, sortField, sortDirection);
        return ResponseEntity.ok(books);
    }

}
