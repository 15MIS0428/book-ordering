package com.example.ordering.book.bookordering.models.requests;

import lombok.Getter;

/**
 * @author Srivani Vaidya
 * DTO for holding book details
 */
@Getter
public class Book {

    private String bookName;

    private String authorName;

    private long quantity;
}
