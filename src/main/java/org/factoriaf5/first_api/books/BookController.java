package org.factoriaf5.first_api.books;

import org.apache.catalina.valves.rewrite.Substitution;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    
    private final InMemoryBookRepository bookRepository;
    
    public BookController() {
        this.bookRepository = new InMemoryBookRepository();
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
        // comprobar que no existe el isbn si existe return (bad_request)
        String isbn = book.getIsbn();
        Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
        if (optionalBook.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        bookRepository.save(book);
        return book; // OK (200) or Created (201)
    }
    
    @DeleteMapping("/{isbn}")
    public String deleteBookByIsbn(@PathVariable String isbn) {
        // si no existe retornar un 404
        Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
        if (!optionalBook.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        // si se ha borrado retornar ok
        bookRepository.deleteByIsbn(isbn);
        return "EL libro se ha borrado correctamente";
    }
    
    // Update -> modificar un libro por su isbn (PUT)
    @PutMapping("/{isbn}")
    public ResponseEntity<String> updateBook(@PathVariable String isbn, @RequestBody Book updatedBook) {
        // Buscar el libro por su ISBN
        Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
        
        // Si el libro no existe, lanzar una excepción con código 404
        if (!optionalBook.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El libro con ISBN " + isbn + " no existe.");
        }
        
        // Recuperar el libro existente y actualizar sus propiedades
        Book existingBook = optionalBook.get();
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setIsbn(updatedBook.getIsbn()); // Esto puede omitirse si el ISBN no cambia
        
        // Guardar el libro actualizado en el repositorio
        bookRepository.save(existingBook);
        
        // Retornar mensaje de éxito
        return ResponseEntity.ok("El libro con ISBN " + isbn + " se ha actualizado correctamente.");
    }
}
