package br.com.ifpb.ads.bookifyapi.controller;

import br.com.ifpb.ads.bookifyapi.dto.AuthorDTO;
import br.com.ifpb.ads.bookifyapi.dto.BookCreateDTO;
import br.com.ifpb.ads.bookifyapi.dto.BookDTO;
import br.com.ifpb.ads.bookifyapi.repository.AuthorRepository;
import br.com.ifpb.ads.bookifyapi.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final AuthorRepository authorRepository;

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new BookCreateDTO());
        model.addAttribute("authors", authorRepository.findAll());
        return "books/form";
    }

    @PostMapping
    public String createBook(@ModelAttribute BookCreateDTO bookCreateDTO) {
        bookService.create(bookCreateDTO);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        BookDTO bookDTO = bookService.findById(id);

        // Converter BookDTO para BookCreateDTO
        BookCreateDTO bookCreateDTO = new BookCreateDTO(
                bookDTO.getId(),
                bookDTO.getTitle(),
                bookDTO.getPrice(),
                bookDTO.getQuantity_stock(),
                bookDTO.getAutores() != null ? // Verifique se a lista de autores não é nula
                        bookDTO.getAutores().stream().map(AuthorDTO::getId).toList() :
                        new ArrayList<>() // Se for nula, passe uma lista vazia
        );

        model.addAttribute("book", bookCreateDTO);
        model.addAttribute("authors", authorRepository.findAll());
        return "books/form";
    }

    @PostMapping("/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute BookCreateDTO bookCreateDTO) {
        bookService.update(id, bookCreateDTO);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/books";
    }
}
