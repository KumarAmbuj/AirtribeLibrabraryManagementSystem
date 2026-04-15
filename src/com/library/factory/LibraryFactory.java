package com.library.factory;

import com.library.model.Book;
import com.library.model.Patron;

public class LibraryFactory {
    public static Book createBook(String title, String author, String isbn, int year) {
        return new Book(title, author, isbn, year);
    }

    public static Patron createPatron(String id, String name, String email) {
        return new Patron(id, name, email);
    }
}
