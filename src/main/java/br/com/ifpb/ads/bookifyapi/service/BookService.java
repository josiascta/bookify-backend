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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final ObjectMapper objectMapper;
    private final AuthorRepository authorRepository;

    private BookDTO convertToDTO(Book book) {
        return objectMapper.convertValue(book, BookDTO.class);
    }

    public BookDTO create(BookCreateDTO dto) throws Exception {

        List<Author> autores = authorRepository.findAllById(dto.getAutores_ids());

        if(dto.getAutores_ids().size() != autores.size()) {
            throw new RegraDeNegocioException("Um ou mais autores não existentes!");
        }
        Book bookEntity = objectMapper.convertValue(dto, Book.class);
        bookEntity.setAutores(autores);
        bookEntity = bookRepository.save(bookEntity);
        return convertToDTO(bookEntity);
    }

    private Book getBook(Integer id) throws RegraDeNegocioException {
        Book bookRecuperado = bookRepository.findAll().stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Livro não encontrado!"));
        return bookRecuperado;
    }

    public BookDTO findById(Integer id) throws Exception {
        return objectMapper.convertValue(getBook(id), BookDTO.class);
    }

    public BookDTO editar(BookCreateDTO dto, Integer id) throws RegraDeNegocioException {
        Book livroRecuperado = getBook(id);

        livroRecuperado.setPrice(dto.getPrice());
        livroRecuperado.setTitle(dto.getTitle());
        livroRecuperado.setQuantity_stock(dto.getQuantity_stock());

        List<Author> autores = authorRepository.findAllById(dto.getAutores_ids());
        if (dto.getAutores_ids().size() != autores.size()) {
            throw new RegraDeNegocioException("Um ou mais autores não existentes!");
        }
        livroRecuperado.setAutores(autores);

        livroRecuperado = bookRepository.save(livroRecuperado);

        return convertToDTO(livroRecuperado);
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        Book book = getBook(id);
        bookRepository.delete(book);
    }

    public Page<BookDTO> findAll(int page, int size, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("asc")
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return bookRepository.findAll(pageable).map(this::convertToDTO);
    }
}
