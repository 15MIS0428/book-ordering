package com.example.ordering.book.bookordering.controllers;

import com.example.ordering.book.bookordering.models.requests.OrderPlaceRequest;
import com.example.ordering.book.bookordering.services.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Srivani Vaidya
 * Controller for book orders
 */
@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * Controller to accept and place the order
     * @param orderPlaceRequest DTO holding the order details
     * @return response containing details about successful order placement
     * @throws JsonProcessingException
     */
    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody OrderPlaceRequest orderPlaceRequest) throws JsonProcessingException {
        double totalOrderValue = orderService.placeOrder(orderPlaceRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Order placed successfully for amount "+String.valueOf(totalOrderValue));
    }
}
