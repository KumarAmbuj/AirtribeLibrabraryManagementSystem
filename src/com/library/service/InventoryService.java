package com.library.service;

import com.library.model.Book;
import com.library.search.SearchStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class InventoryService {
    private static final Logger logger = Logger.getLogger(InventoryService.class.getName());
    private List<Book> books;

    public InventoryService() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        logger.info("Added book to inventory: " + book.getTitle());
    }

    public void removeBook(Book book) {
        books.remove(book);
        logger.info("Removed book from inventory: " + book.getTitle());
    }

    public List<Book> searchBooks(SearchStrategy strategy, String query) {
        logger.info("Searching books with query: " + query);
        return strategy.search(books, query);
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }
}
