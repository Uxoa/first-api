package org.factoriaf5.first_api.librosJPA;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    
    private final BookRepository bookRepository;
    
    // Inyección de dependencia a través del constructor del framework Spring
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    @GetMapping
    public List<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }
    
    @GetMapping("/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable String isbn) {
        Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
        
        if (optionalBook.isPresent()) {
            return new ResponseEntity<>(optionalBook.get(), HttpStatus.OK); // 200 OK
        }
        
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
    }
    
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        String isbn = book.getIsbn();
        Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
        if (optionalBook.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        bookRepository.saveBook(book);
        return book;
    }
    
    @DeleteMapping("/{isbn}")
    public String deleteBookByIsbn(@PathVariable String isbn) {
        Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
        if (!optionalBook.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        bookRepository.deleteByIsbn(isbn);
        return "EL libro se ha borrado correctamente";
    }
    
    @PutMapping("/{isbn}")
    public ResponseEntity<String> updateBook(@PathVariable String isbn, @RequestBody Book updatedBook) {
       
        // Instancio un Optional y busco por isbn
        Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
        
        // si no está el libro mensaje not_found
        if (!optionalBook.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El libro con ISBN " + isbn + " no existe.");
        }
        
        // si está recupero datos y sustituyo
        Book choosedBook = optionalBook.get();
        choosedBook.setTitle(updatedBook.getTitle());
        choosedBook.setAuthor(updatedBook.getAuthor());
        
        bookRepository.saveBook(choosedBook);
        
        return ResponseEntity.ok("El libro con ISBN " + isbn + " se ha actualizado correctamente.");
    }
}
