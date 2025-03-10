package br.com.ifpb.ads.bookifyapi.controller;

import br.com.ifpb.ads.bookifyapi.dto.BookCreateDTO;
import br.com.ifpb.ads.bookifyapi.dto.BookDTO;
import br.com.ifpb.ads.bookifyapi.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/livro")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookDTO> create(@Valid @RequestBody BookCreateDTO pessoa) throws Exception {
        return new ResponseEntity<>(bookService.create(pessoa), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public BookDTO findById(@PathVariable Integer id) throws Exception {
        return bookService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> update(@PathVariable Integer id, @RequestBody BookCreateDTO bookCreateDTO) throws Exception {
        return new ResponseEntity<>(bookService.editar(bookCreateDTO, id), HttpStatus.OK);
    }
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
