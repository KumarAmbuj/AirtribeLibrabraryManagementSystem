package com.library.observer;

import com.library.model.Book;

public interface Observer {
    void update(Book book, String message);
}
