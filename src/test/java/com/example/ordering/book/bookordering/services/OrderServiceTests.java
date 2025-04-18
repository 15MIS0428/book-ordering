package com.example.ordering.book.bookordering.services;

import com.example.ordering.book.bookordering.models.requests.OrderPlaceRequest;
import com.example.ordering.book.bookordering.services.impl.OrderProcessingService;
import com.example.ordering.book.bookordering.services.impl.OrderServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;

public class OrderServiceTests {

    @Mock
    private OrderProcessingService orderProcessingService;

    @InjectMocks
    private OrderService orderService = new OrderServiceImpl();

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void placeOrderTest() throws JsonProcessingException {
        OrderPlaceRequest orderPlaceRequest = new OrderPlaceRequest(new ArrayList<>());
        Mockito.when(orderProcessingService.getTotalOrderValue(ArgumentMatchers.anyList())).thenReturn(100.0);
        double totalValue = orderService.placeOrder(orderPlaceRequest);
        Assertions.assertEquals(100.0,totalValue,0.001);

    }

}
