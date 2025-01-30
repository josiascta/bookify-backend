package br.com.ifpb.ads.bookifyapi.controller;

import br.com.ifpb.ads.bookifyapi.dto.AuthorDTO;
import br.com.ifpb.ads.bookifyapi.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/autor")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/{id}")
    public AuthorDTO findById(@PathVariable Long id) throws Exception {
        return authorService.findById(id);
    }
}
