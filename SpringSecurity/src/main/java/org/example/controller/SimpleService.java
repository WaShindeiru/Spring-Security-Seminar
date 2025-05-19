package org.example.controller;

import org.example.security.SecurityUser;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SimpleService {

    private final Map<String, List<Book>> books;

    public SimpleService() {
        books = new HashMap<>();
        books.put("Ola", new ArrayList<Book>());
        books.put("Maciek", new ArrayList<Book>());
        books.put("Zuza", new ArrayList<Book>());

        Book outsider = new Book("Outsider Book");
        Book book_1984 = new Book("1984");
        Book Bible = new Book("Bible");

        books.get("Ola").add(outsider);
        books.get("Ola").add(Bible);

        books.get("Maciek").add(outsider);
        books.get("Maciek").add(Bible);

        books.get("Zuza").add(book_1984);
        books.get("Zuza").add(Bible);
    }

    @PreAuthorize("hasRole('MANAGER')")
    public StatusDto getMethodSecurityResult() {
        return new StatusDto("Ok", "Get method security result!");
    }

    public List<Book> getBooks(SecurityUser user) {
        Book book_forbidden = new Book("1984");

        List<Book> books_temp = books.get(user.getUsername());
        if (books_temp.contains(book_forbidden)) {
            throw new AccessDeniedException("Access denied");
        }
        return books_temp;
    }
}
