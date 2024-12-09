package br.com.ifpb.ads.bookifyapi.service;

import br.com.ifpb.ads.bookifyapi.dto.BookCreateDTO;
import br.com.ifpb.ads.bookifyapi.dto.BookDTO;
import br.com.ifpb.ads.bookifyapi.entity.Autor;
import br.com.ifpb.ads.bookifyapi.entity.Book;
import br.com.ifpb.ads.bookifyapi.exception.RegraDeNegocioException;
import br.com.ifpb.ads.bookifyapi.repository.AutorRepository;
import br.com.ifpb.ads.bookifyapi.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final ObjectMapper objectMapper;
    private final AutorRepository autorRepository;

    public BookDTO create(BookCreateDTO dto) throws Exception {

        List<Autor> autores = autorRepository.findAllById(dto.getAutores_ids());
        Book bookEntity = objectMapper.convertValue(dto, Book.class);
        bookEntity.setAutores(autores);
        bookEntity = bookRepository.save(bookEntity);
        BookDTO bookDTO = objectMapper.convertValue(bookEntity, BookDTO.class);
        return bookDTO;
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


}
