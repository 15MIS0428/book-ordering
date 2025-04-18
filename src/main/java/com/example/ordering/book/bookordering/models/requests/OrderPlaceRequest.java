package com.example.ordering.book.bookordering.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderPlaceRequest{

    @JsonProperty("books")
    private List<Book> bookList;

}
