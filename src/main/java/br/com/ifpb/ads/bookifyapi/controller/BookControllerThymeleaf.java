package br.com.ifpb.ads.bookifyapi.controller;

import br.com.ifpb.ads.bookifyapi.dto.AuthorDTO;
import br.com.ifpb.ads.bookifyapi.dto.BookCreateDTO;
import br.com.ifpb.ads.bookifyapi.dto.BookDTO;
import br.com.ifpb.ads.bookifyapi.entity.Author;
import br.com.ifpb.ads.bookifyapi.entity.Book;
import br.com.ifpb.ads.bookifyapi.repository.AuthorRepository;
import br.com.ifpb.ads.bookifyapi.service.AuthorService;
import br.com.ifpb.ads.bookifyapi.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/livros-view")
@RequiredArgsConstructor
public class BookControllerThymeleaf {

    private final BookService bookService;
    private final ObjectMapper objectMapper;
    private final AuthorRepository authorRepository;
    private AuthorService authorService;


    @GetMapping
    public String listarLivros(Model model,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size,
                               @RequestParam(defaultValue = "id") String sortField,
                               @RequestParam(defaultValue = "asc") String sortDirection) {
        model.addAttribute("livros", bookService.findAll(page, size, sortField, sortDirection));
        return "livro-front";
    }

    @GetMapping("/novo")
    public String novoLivroForm(Model model) {
        model.addAttribute("livro", new BookCreateDTO());
        model.addAttribute("autores", authorRepository.findAll());  // Certifique-se de que findAll() retorna List<Author>
        return "livro-form";
    }

    @GetMapping("/editar/{id}")
    public String editarLivroForm(@PathVariable Long id, Model model) throws Exception {
        BookDTO livroDTO = bookService.findById(id);

        BookCreateDTO livroForm = new BookCreateDTO();
        livroForm.setTitle(livroDTO.getTitle());
        livroForm.setPrice(livroDTO.getPrice());
        livroForm.setQuantity_stock(livroDTO.getQuantity_stock());

        List<Long> autoresIds = livroDTO.getAutores().stream()
                .map(AuthorDTO::getId)
                .collect(Collectors.toList());
        livroForm.setAutores_ids(autoresIds);  // Passando a lista de IDs

        model.addAttribute("livro", livroForm);
        model.addAttribute("autores", authorRepository.findAll());  // Passando os autores
        return "livro-form";
    }

    @PostMapping("/salvar")
    public String salvarLivro(@ModelAttribute BookCreateDTO livro) throws Exception {
        bookService.create(livro);
        return "redirect:/livros-view";
    }

    @GetMapping("/excluir/{id}")
    public String excluirLivro(@PathVariable Long id) throws Exception {
        bookService.delete(id);
        return "redirect:/livros-view";
    }
    @RequestMapping("/livro-form")
    public String showLivroForm(Model model) {
        Book livro = new Book();
        List<Author> autores = authorService.findAll();  // Carregue os autores de alguma forma
        model.addAttribute("livro", livro);
        model.addAttribute("autores", autores);  // Envia a lista de autores para o template
        return "livro-form";
    }

}
