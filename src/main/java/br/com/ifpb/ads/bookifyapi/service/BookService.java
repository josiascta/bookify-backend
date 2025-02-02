package br.com.ifpb.ads.bookifyapi.service;

import br.com.ifpb.ads.bookifyapi.dto.AuthorDTO;
import br.com.ifpb.ads.bookifyapi.dto.BookCreateDTO;
import br.com.ifpb.ads.bookifyapi.dto.BookDTO;
import br.com.ifpb.ads.bookifyapi.entity.Author;
import br.com.ifpb.ads.bookifyapi.entity.Book;
import br.com.ifpb.ads.bookifyapi.exception.RegraDeNegocioException;
import br.com.ifpb.ads.bookifyapi.repository.AuthorRepository;
import br.com.ifpb.ads.bookifyapi.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public Page<BookDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("title"));
        return bookRepository.findAll(pageable).map(this::convertToDTO);
    }

    public BookDTO findById(Long id) {
        return bookRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    }

    public void create(BookCreateDTO bookCreateDTO) {
        Book book = new Book();
        book.setTitle(bookCreateDTO.getTitle());
        book.setPrice(bookCreateDTO.getPrice());
        book.setQuantity_stock(bookCreateDTO.getQuantity_stock());

        List<Author> authors = authorRepository.findAllById(bookCreateDTO.getAutores());
        book.setAutores(authors);

        bookRepository.save(book);
    }

    public void update(Long id, BookCreateDTO bookCreateDTO) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        book.setTitle(bookCreateDTO.getTitle());
        book.setPrice(bookCreateDTO.getPrice());
        book.setQuantity_stock(bookCreateDTO.getQuantity_stock());

        List<Author> authors = authorRepository.findAllById(bookCreateDTO.getAutores());
        book.setAutores(authors);

        bookRepository.save(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    private BookDTO convertToDTO(Book book) {
        List<AuthorDTO> authorDTOS = book.getAutores().stream()
                .map(author -> new AuthorDTO(author.getId(), author.getNome()))
                .toList();

        return new BookDTO(book.getIdLivro(), book.getTitle(), book.getPrice(), book.getQuantity_stock(), authorDTOS);
    }
}


