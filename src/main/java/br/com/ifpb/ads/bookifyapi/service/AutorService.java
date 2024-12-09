package br.com.ifpb.ads.bookifyapi.service;

import br.com.ifpb.ads.bookifyapi.dto.AutorDTO;
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
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AutorService {

    private final ObjectMapper objectMapper;
    private final AutorRepository autorRepository;
    private final BookRepository bookRepository;

    private Autor getAutor(Integer id) throws RegraDeNegocioException {
        Autor autorRecuperado = autorRepository.findAll().stream()
                .filter(autor -> autor.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Autor não encontrado!"));
        return autorRecuperado;
    }

    public AutorDTO findById(Integer id) throws Exception {
        return objectMapper.convertValue(getAutor(id), AutorDTO.class);
    }
}
