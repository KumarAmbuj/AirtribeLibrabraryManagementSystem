package com.library.service;

import com.library.model.Book;
import com.library.model.BookStatus;
import com.library.model.Patron;
import java.util.logging.Logger;

public class ReservationService {
    private static final Logger logger = Logger.getLogger(ReservationService.class.getName());

    public void reserveBook(Book book, Patron patron) {
        if (book.getStatus() == BookStatus.BORROWED) {
            book.setStatus(BookStatus.RESERVED);
            book.registerObserver(patron);
            logger.info("Patron " + patron.getName() + " reserved the book '" + book.getTitle() + "'");
        } else if (book.getStatus() == BookStatus.AVAILABLE) {
            logger.info("Book '" + book.getTitle() + "' is available. No need to reserve, you can check it out.");
        } else {
            book.registerObserver(patron);
            logger.info("Patron " + patron.getName() + " added to reservation list for '" + book.getTitle() + "'");
        }
    }
}
