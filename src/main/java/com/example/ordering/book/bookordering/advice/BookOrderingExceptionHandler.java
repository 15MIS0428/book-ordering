package com.example.ordering.book.bookordering.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Srivani Vaidya
 * Global Exception handler for Book Ordering application
 */
@ControllerAdvice
@Slf4j
public class BookOrderingExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> handleBookNotFoundException(String message){
        log.info(message);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
}
