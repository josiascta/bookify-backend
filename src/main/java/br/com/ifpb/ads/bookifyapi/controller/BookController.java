package br.com.ifpb.ads.bookifyapi.controller;

import br.com.ifpb.ads.bookifyapi.dto.BookDTO;
import br.com.ifpb.ads.bookifyapi.entity.Book;
import br.com.ifpb.ads.bookifyapi.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/livro")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookDTO> create(@Valid @RequestBody BookDTO pessoa) throws Exception {
        return new ResponseEntity<>(bookService.create(pessoa), HttpStatus.OK);
    }

}
