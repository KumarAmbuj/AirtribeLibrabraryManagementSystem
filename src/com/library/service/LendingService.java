package com.library.service;

import com.library.model.Book;
import com.library.model.BookStatus;
import com.library.model.Patron;
import java.util.logging.Logger;

public class LendingService {
    private static final Logger logger = Logger.getLogger(LendingService.class.getName());

    public void checkoutBook(Book book, Patron patron) {
        if (book.getStatus() == BookStatus.AVAILABLE) {
            book.setStatus(BookStatus.BORROWED);
            patron.addBorrowedBook(book);
            logger.info("Book '" + book.getTitle() + "' successfully checked out by " + patron.getName());
        } else {
            logger.warning("Checkout failed: Book '" + book.getTitle() + "' is currently " + book.getStatus());
            throw new IllegalStateException("Book is not available for checkout.");
        }
    }

    public void returnBook(Book book) {
        if (book.getStatus() == BookStatus.BORROWED || book.getStatus() == BookStatus.RESERVED) {
            logger.info("Book '" + book.getTitle() + "' returned.");
            book.setStatus(BookStatus.AVAILABLE); 
        } else {
            logger.warning("Return failed: Book '" + book.getTitle() + "' was not borrowed.");
        }
    }
}
