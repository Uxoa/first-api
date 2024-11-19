package org.factoriaf5.books;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<Book> findAll();
    Optional<Book> findByIsbn(String isbn);
    void saveBook(Book book);
    void deleteByIsbn(String isbn);
    
}