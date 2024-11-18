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
    public void saveBook(Book book) {
        // Buscar si el libro ya existe por su ISBN
        Optional<Book> choosedBook = findByIsbn(book.getIsbn());
        
        if (choosedBook.isPresent()) {
            // Si el libro existe, actualizarlo
            booksDB.remove(choosedBook.get());
        }
        
        // Añadir el libro actualizado o nuevo
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
