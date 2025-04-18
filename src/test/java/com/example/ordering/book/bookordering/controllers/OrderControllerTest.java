package com.example.ordering.book.bookordering.controllers;

import com.example.ordering.book.bookordering.models.requests.OrderPlaceRequest;
import com.example.ordering.book.bookordering.services.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test to verify if order place request is returning correct response in case of placig order successfully
     * @throws JsonProcessingException in case there are issues in de-serializing BookItem entity
     */
    @Test
    public void placeOrderTest() throws JsonProcessingException {
        Mockito.when(orderService.placeOrder(ArgumentMatchers.any(OrderPlaceRequest.class))).thenReturn(100.0);
        ResponseEntity<String> response = orderController.placeOrder(new OrderPlaceRequest());
        Assertions.assertEquals(HttpStatus.CREATED,response.getStatusCode());
        Assertions.assertEquals("Order placed successfully for amount "+String.valueOf(100.0), response.getBody());
    }
}
