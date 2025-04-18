package com.example.ordering.book.bookordering.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Srivani Vaidya
 * DTO to hold order placement details
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderPlaceRequest{

    @JsonProperty("books")
    private List<Book> bookList;

}
