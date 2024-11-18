package org.factoriaf5.first_api.books;

import java.util.*;

public class InMemoryBookRepository implements BookRepository {
    
    private final static List<Book> booksDB = new ArrayList<>();
    
    public InMemoryBookRepository() {
        booksDB.add(new Book("A123", "Título del libro 1", "Autor del libro 1"));
        booksDB.add(new Book("A124", "Título del libro 2", "Autor del libro 2"));
    }
    
    @Override
    public List<Book> findAll() {
        return Collections.unmodifiableList(booksDB);
    }
    
    @Override
    public Optional<Book> findByIsbn(String isbn) {
        for (Book book : booksDB) {
            if (book.getIsbn().equals(isbn)) return Optional.of(book);
        }
        return Optional.empty();
    }
    
    @Override
    public void save(Book book) {
        booksDB.add(book);
    }
    
    @Override
    public void deleteByIsbn(String isbn) {
        booksDB.removeIf(book -> book.getIsbn().equals(isbn));
    }
    
    @Override
    public void updateByIsbn(String isbn){
    
    
    }
}
