package com.library.search;

import com.library.model.Book;
import java.util.List;
import java.util.stream.Collectors;

public class IsbnSearchStrategy implements SearchStrategy {
    @Override
    public List<Book> search(List<Book> books, String query) {
        return books.stream()
                .filter(b -> b.getIsbn().equals(query))
                .collect(Collectors.toList());
    }
}
