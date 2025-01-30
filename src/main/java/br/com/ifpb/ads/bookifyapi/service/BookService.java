package br.com.ifpb.ads.bookifyapi.service;

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

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final ObjectMapper objectMapper;
    private final AuthorRepository authorRepository;

    private BookDTO convertToDTO(Book book) {
        return objectMapper.convertValue(book, BookDTO.class);
    }

    @Transactional
    public BookDTO create(BookCreateDTO dto) throws Exception {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setPrice(dto.getPrice());
        book.setQuantity_stock(dto.getQuantity_stock());

        // Recuperando os autores pelo ID (garantindo que o tipo da lista é Long)
        List<Author> autores = authorRepository.findAllById(dto.getAutores_ids());
        if (autores.isEmpty()) {
            throw new Exception("Autores não encontrados!");
        }
        book.setAutores(autores);

        // Salvando o livro
        Book savedBook = bookRepository.save(book);
        return convertToDTO(savedBook);  // Convertendo o livro salvo para DTO
    }
    private Book getBook(Long id) throws Exception {
        return bookRepository.findById(id)
                .orElseThrow(() -> new Exception("Livro não encontrado!"));
    }

    public BookDTO findById(Long id) throws Exception {
        return objectMapper.convertValue(getBook(id), BookDTO.class);
    }

    @Transactional
    public BookDTO editar(BookCreateDTO dto, Long id) throws Exception {
        Book livro = getBook(id);
        livro.setTitle(dto.getTitle());
        livro.setPrice(dto.getPrice());
        livro.setQuantity_stock(dto.getQuantity_stock());

        // Recuperando a lista de autores
        List<Author> autores = authorRepository.findAllById(dto.getAutores_ids());
        if (autores.isEmpty()) {
            throw new Exception("Autores não encontrados!");
        }
        livro.setAutores(autores);

        Book updatedBook = bookRepository.save(livro);
        return convertToDTO(updatedBook);
    }

    @Transactional
    public void delete(Long id) throws Exception {
        Book book = getBook(id);
        bookRepository.delete(book);
    }

    public Page<BookDTO> findAll(int page, int size, String sortField, String sortDirection) {
        // Ajustar a ordenação para usar o nome correto do campo idLivro
        Sort sort = sortDirection.equalsIgnoreCase("asc")
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        // Se sortField for 'id', use 'idLivro'
        if ("id".equals(sortField)) {
            sort = sortDirection.equalsIgnoreCase("asc")
                    ? Sort.by("idLivro").ascending()
                    : Sort.by("idLivro").descending();
        }

        Pageable pageable = PageRequest.of(page, size, sort);
        return bookRepository.findAll(pageable).map(this::convertToDTO);
    }

}