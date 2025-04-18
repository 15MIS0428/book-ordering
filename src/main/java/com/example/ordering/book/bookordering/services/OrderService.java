package com.example.ordering.book.bookordering.services;

import com.example.ordering.book.bookordering.models.requests.OrderPlaceRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface OrderService {

    double placeOrder(final OrderPlaceRequest orderPlaceRequest) throws JsonProcessingException;
}
