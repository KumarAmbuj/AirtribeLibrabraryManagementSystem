package com.library.service;

import com.library.model.Book;
import com.library.model.Patron;
import java.util.List;
import java.util.stream.Collectors;

public class RecommendationService {
    private InventoryService inventoryService;

    public RecommendationService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public List<Book> recommendBooks(Patron patron) {
        List<String> favoriteAuthors = patron.getBorrowingHistory().stream()
                .map(Book::getAuthor)
                .distinct()
                .collect(Collectors.toList());

        return inventoryService.getAllBooks().stream()
                .filter(book -> favoriteAuthors.contains(book.getAuthor()))
                .filter(book -> !patron.getBorrowingHistory().contains(book))
                .limit(5)
                .collect(Collectors.toList());
    }
}
