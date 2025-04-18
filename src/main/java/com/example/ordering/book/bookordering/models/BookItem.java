package com.example.ordering.book.bookordering.models;

import lombok.Data;

@Data
public class BookItem {

    private long itemId;

    private String bookName;

    private int quantity;

    private double price;
}
