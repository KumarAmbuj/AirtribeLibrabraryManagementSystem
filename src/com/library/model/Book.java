package com.library.model;

import com.library.observer.Observer;
import com.library.observer.Subject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Book implements Subject {
    private String title;
    private String author;
    private String isbn;
    private int publicationYear;
    private BookStatus status;
    private List<Observer> observers;

    public Book(String title, String author, String isbn, int publicationYear) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.status = BookStatus.AVAILABLE;
        this.observers = new ArrayList<>();
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    
    public int getPublicationYear() { return publicationYear; }
    public void setPublicationYear(int publicationYear) { this.publicationYear = publicationYear; }
    
    public BookStatus getStatus() { return status; }
    public void setStatus(BookStatus status) {
        this.status = status;
        if (status == BookStatus.AVAILABLE) {
            notifyObservers("Book '" + title + "' is now available!");
        }
    }

    @Override
    public void registerObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }
    
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : new ArrayList<>(observers)) {
            observer.update(this, message);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    @Override
    public String toString() {
        return "Book{" + "title='" + title + '\'' + ", author='" + author + '\'' + ", isbn='" + isbn + '\'' + ", status=" + status + '}';
    }
}
