package br.com.ifpb.ads.bookifyapi.controller;

import br.com.ifpb.ads.bookifyapi.dto.AuthorDTO;
import br.com.ifpb.ads.bookifyapi.dto.BookCreateDTO;
import br.com.ifpb.ads.bookifyapi.dto.BookDTO;
import br.com.ifpb.ads.bookifyapi.entity.Book;
import br.com.ifpb.ads.bookifyapi.repository.AuthorRepository;
import br.com.ifpb.ads.bookifyapi.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/livros-view")
@RequiredArgsConstructor
public class BookControllerThymeleaf {

    private final BookService bookService;
    private final ObjectMapper objectMapper;
    private final AuthorRepository authorRepository;

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
        model.addAttribute("livro", new BookCreateDTO()); // Alterado para BookCreateDTO
        model.addAttribute("autores", authorRepository.findAll()); // Passa a lista de autores
        return "livro-form";
    }

    @GetMapping("/editar/{id}")
    public String editarLivroForm(@PathVariable Integer id, Model model) throws Exception {
        BookDTO livroDTO = bookService.findById(id);

        BookCreateDTO livroForm = new BookCreateDTO();
        livroForm.setTitle(livroDTO.getTitle());
        livroForm.setPrice(livroDTO.getPrice());
        livroForm.setQuantity_stock(livroDTO.getQuantity_stock());

        List<Integer> autorIds = livroDTO.getAutores().stream()
                .map(autor -> autor.getId())
                .collect(Collectors.toList());
        livroForm.setAutores_ids(autorIds);

        model.addAttribute("livro", livroForm);
        model.addAttribute("autores", authorRepository.findAll());
        return "livro-form";
    }

    @PostMapping("/salvar")
    public String salvarLivro(@ModelAttribute Book livro) throws Exception {
        bookService.create(objectMapper.convertValue(livro, BookCreateDTO.class));
        return "redirect:/livros-view";
    }

    @GetMapping("/excluir/{id}")
    public String excluirLivro(@PathVariable Integer id) throws Exception {
        bookService.delete(id);
        return "redirect:/livros-view";
    }
}
