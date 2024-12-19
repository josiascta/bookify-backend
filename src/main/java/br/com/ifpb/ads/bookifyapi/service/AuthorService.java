package br.com.ifpb.ads.bookifyapi.service;

import br.com.ifpb.ads.bookifyapi.dto.AuthorDTO;
import br.com.ifpb.ads.bookifyapi.entity.Author;
import br.com.ifpb.ads.bookifyapi.exception.RegraDeNegocioException;
import br.com.ifpb.ads.bookifyapi.repository.AuthorRepository;
import br.com.ifpb.ads.bookifyapi.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthorService {

    private final ObjectMapper objectMapper;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    private Author getAutor(Integer id) throws RegraDeNegocioException {
        Author authorRecuperado = authorRepository.findAll().stream()
                .filter(autor -> autor.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Autor não encontrado!"));
        return authorRecuperado;
    }

    public AuthorDTO findById(Integer id) throws Exception {
        return objectMapper.convertValue(getAutor(id), AuthorDTO.class);
    }
}
