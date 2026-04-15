package com.library;

import com.library.factory.LibraryFactory;
import com.library.model.Book;
import com.library.model.Patron;
import com.library.search.AuthorSearchStrategy;
import com.library.search.TitleSearchStrategy;
import com.library.service.*;

import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    public static void main(String[] args) {
        // Setup simple formatting for logger
        configureLogger();

        System.out.println("--- Bootstrapping Library Management System ---");
        
        InventoryService inventoryService = new InventoryService();
        PatronService patronService = new PatronService();
        LendingService lendingService = new LendingService();
        ReservationService reservationService = new ReservationService();
        RecommendationService recommendationService = new RecommendationService(inventoryService);

        // Factory Pattern
        Book book1 = LibraryFactory.createBook("Effective Java", "Joshua Bloch", "978-0134685991", 2018);
        Book book2 = LibraryFactory.createBook("Clean Code", "Robert C. Martin", "978-0132350884", 2008);
        Book book3 = LibraryFactory.createBook("Java Concurrency in Practice", "Brian Goetz", "978-0321349606", 2006);
        Book book4 = LibraryFactory.createBook("Clean Architecture", "Robert C. Martin", "978-0134494166", 2017);

        inventoryService.addBook(book1);
        inventoryService.addBook(book2);
        inventoryService.addBook(book3);
        inventoryService.addBook(book4);

        Patron patron1 = LibraryFactory.createPatron("P1", "Alice Smith", "alice@example.com");
        Patron patron2 = LibraryFactory.createPatron("P2", "Bob Johnson", "bob@example.com");
        
        patronService.addPatron(patron1);
        patronService.addPatron(patron2);

        System.out.println("\n--- Searching Books (Strategy Pattern) ---");
        List<Book> cleanBooks = inventoryService.searchBooks(new TitleSearchStrategy(), "Clean");
        System.out.println("Search 'Clean' by Title: ");
        cleanBooks.forEach(System.out::println);

        List<Book> martinBooks = inventoryService.searchBooks(new AuthorSearchStrategy(), "Martin");
        System.out.println("Search 'Martin' by Author: ");
        martinBooks.forEach(System.out::println);

        System.out.println("\n--- Checkout and Lending ---");
        lendingService.checkoutBook(book2, patron1);
        lendingService.checkoutBook(book1, patron1);

        System.out.println("\n--- Reservation System (Observer Pattern) ---");
        // Bob tries to reserve 'Clean Code' which is currently checked out by Alice
        reservationService.reserveBook(book2, patron2);

        System.out.println("\n--- Returning Book ---");
        // Alice returns the book, and Bob should be notified via Observer
        lendingService.returnBook(book2);

        System.out.println("\n--- Recommendation System ---");
        List<Book> recommendations = recommendationService.recommendBooks(patron1);
        System.out.println("Recommendations for " + patron1.getName() + " based on history: ");
        recommendations.forEach(System.out::println);
    }

    private static void configureLogger() {
        Logger rootLogger = Logger.getLogger("");
        for (java.util.logging.Handler handler : rootLogger.getHandlers()) {
            if (handler instanceof ConsoleHandler) {
                handler.setFormatter(new SimpleFormatter() {
                    private static final String format = "[%1$tT] [%2$s] %3$s%n";
                    @Override
                    public synchronized String format(java.util.logging.LogRecord lr) {
                        return String.format(format,
                                new java.util.Date(lr.getMillis()),
                                lr.getLevel().getLocalizedName(),
                                lr.getMessage()
                        );
                    }
                });
            }
        }
        rootLogger.setLevel(Level.INFO);
    }
}
