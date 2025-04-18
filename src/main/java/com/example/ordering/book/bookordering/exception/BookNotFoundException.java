package com.example.ordering.book.bookordering.exception;


public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(String message){
        super(message);
    }
}
