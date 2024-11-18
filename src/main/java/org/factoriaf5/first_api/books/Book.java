package org.factoriaf5.first_api.books;

public class Book {
    private String isbn;
    private String name;
    private String author;
    
    public Book(String isbn, String name, String author) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
    }
    
    
    public String getIsbn() {
        return isbn;
    }
    
    public String getName() {
        return name;
    }
    
    public String getAuthor() {
        return author;
    }
}
