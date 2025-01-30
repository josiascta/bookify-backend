package br.com.ifpb.ads.bookifyapi.service;

import br.com.ifpb.ads.bookifyapi.dto.AuthorDTO;
import br.com.ifpb.ads.bookifyapi.entity.Author;
import br.com.ifpb.ads.bookifyapi.exception.RegraDeNegocioException;
import br.com.ifpb.ads.bookifyapi.repository.AuthorRepository;
import br.com.ifpb.ads.bookifyapi.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthorService {

    private final ObjectMapper objectMapper;
    private final AuthorRepository authorRepository;

    private Author getAutor(Long id) throws RegraDeNegocioException {
        return authorRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Autor não encontrado!"));
    }

    public AuthorDTO findById(Long id) throws Exception {
        Author author = getAutor(id);
        return objectMapper.convertValue(author, AuthorDTO.class);
    }
    public List<Author> findAll() {
        return authorRepository.findAll(); // Certifique-se de que findAll() retorna uma lista de autores
    }
}
