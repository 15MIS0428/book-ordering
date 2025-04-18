package com.example.ordering.book.bookordering.services.impl;

import com.example.ordering.book.bookordering.models.requests.OrderPlaceRequest;
import com.example.ordering.book.bookordering.services.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Srivani Vaidya
 * Service class handling the operations relevant to placing order
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired private OrderProcessingService orderProcessingService;

    /***
     * Places order for a list of books
     * @param orderPlaceRequest DTO holding the order details
     * @throws JsonProcessingException in case of issues in fetching the BookItem
     * @return
     */
    @Override
    public double placeOrder(OrderPlaceRequest orderPlaceRequest) throws JsonProcessingException {
        return orderProcessingService.getTotalOrderValue(orderPlaceRequest.getBookList());
    }


}
