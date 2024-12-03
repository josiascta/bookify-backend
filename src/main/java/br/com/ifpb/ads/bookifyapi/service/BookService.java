package br.com.ifpb.ads.bookifyapi.service;

import br.com.ifpb.ads.bookifyapi.dto.BookDTO;
import br.com.ifpb.ads.bookifyapi.entity.Book;
import br.com.ifpb.ads.bookifyapi.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final ObjectMapper objectMapper;

    public BookDTO create(BookDTO dto) throws Exception {

        Book bookEntity = objectMapper.convertValue(dto, Book.class);
        bookEntity = bookRepository.save(bookEntity);
        BookDTO bookDTO = objectMapper.convertValue(bookEntity, BookDTO.class);
        return bookDTO;
    }
}
