package br.com.ifpb.ads.bookifyapi.repository;


import br.com.ifpb.ads.bookifyapi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
