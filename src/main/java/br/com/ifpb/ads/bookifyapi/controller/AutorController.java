package br.com.ifpb.ads.bookifyapi.controller;

import br.com.ifpb.ads.bookifyapi.dto.AutorCreateDTO;
import br.com.ifpb.ads.bookifyapi.dto.AutorDTO;
import br.com.ifpb.ads.bookifyapi.dto.BookCreateDTO;
import br.com.ifpb.ads.bookifyapi.dto.BookDTO;
import br.com.ifpb.ads.bookifyapi.service.AutorService;
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
@RequestMapping("/autor")
public class AutorController {

    private final AutorService autorService;

    @GetMapping("/{id}")
    public AutorDTO findById(@PathVariable Integer id) throws Exception {
        return autorService.findById(id);
    }
}
