package br.com.ifpb.ads.bookifyapi.repository;

import br.com.ifpb.ads.bookifyapi.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
