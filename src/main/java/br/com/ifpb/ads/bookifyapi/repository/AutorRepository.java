package br.com.ifpb.ads.bookifyapi.repository;

import br.com.ifpb.ads.bookifyapi.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Integer> {
}
