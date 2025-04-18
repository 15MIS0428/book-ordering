package com.example.ordering.book.bookordering.models;

import lombok.Data;

/**
 * @author Srivani Vaidya
 * DTO to hold book details in book-inventory
 */
@Data
public class BookItem {

    private long itemId;

    private String bookName;

    private int quantity;

    private double price;
}
