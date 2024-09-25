package com.cihexample.catalogservice.domain;

import java.util.Optional;

public interface BookRepository {
    Iterable<Book> findAll();

    Optional<Book> findByIsbn(String isbn);

    boolean existsIsbn(String isbn);

    Book save(Book book);

    void deleteByIsBn(String isbn);
}
