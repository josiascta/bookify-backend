package br.com.ifpb.ads.bookifyapi.repository;


import br.com.ifpb.ads.bookifyapi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

}
