package com.library.model;

import com.library.observer.Observer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Patron implements Observer {
    private static final Logger logger = Logger.getLogger(Patron.class.getName());
    private String id;
    private String name;
    private String email;
    private List<Book> borrowingHistory;

    public Patron(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.borrowingHistory = new ArrayList<>();
    }

    public String getId() { return id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public List<Book> getBorrowingHistory() { return borrowingHistory; }

    public void addBorrowedBook(Book book) {
        this.borrowingHistory.add(book);
    }

    @Override
    public void update(Book book, String message) {
        logger.info("Notification for Patron '" + name + "' (" + email + "): " + message);
    }

    @Override
    public String toString() {
        return "Patron{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", email='" + email + '\'' + '}';
    }
}
