package br.com.ifpb.ads.bookifyapi.service;

import br.com.ifpb.ads.bookifyapi.dto.AuthorDTO;
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

    public Page<AuthorDTO> findAll(int page, int size, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("asc")
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return authorRepository.findAll(pageable).map(this::convertToDTO);
    }

    private AuthorDTO convertToDTO(Author author) {
        return objectMapper.convertValue(author, AuthorDTO.class);
    }

}
