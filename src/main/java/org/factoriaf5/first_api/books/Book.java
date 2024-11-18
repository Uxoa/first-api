package org.factoriaf5.first_api.books;

public class Book {
    private String isbn;
    private String title;
    private String author;
    
    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }
    
    
    public String getIsbn() {
        return isbn;
    }
    
    public String getName() {
        return title;
    }
    
    public String getAuthor() {
        return author;
    }
}
