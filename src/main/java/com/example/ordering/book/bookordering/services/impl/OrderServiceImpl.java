package com.example.ordering.book.bookordering.services.impl;

import com.example.ordering.book.bookordering.exception.BookNotFoundException;
import com.example.ordering.book.bookordering.models.BookItem;
import com.example.ordering.book.bookordering.models.BookOrder;
import com.example.ordering.book.bookordering.models.OrderValue;
import com.example.ordering.book.bookordering.models.requests.Book;
import com.example.ordering.book.bookordering.models.requests.OrderPlaceRequest;
import com.example.ordering.book.bookordering.repositories.BookOrderRepository;
import com.example.ordering.book.bookordering.repositories.OrderValueRepository;
import com.example.ordering.book.bookordering.services.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

/**
 * @author Srivani Vaidya
 * Service class handling the operations relevant to placing order
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private WebClient webClient;

    @Autowired
    private OrderValueRepository orderValueRepository;

    @Autowired
    private BookOrderRepository bookOrderRepository;

    /***
     * Places order for a list of books
     * @param orderPlaceRequest DTO holding the order details
     * @throws JsonProcessingException in case of issues in fetching the BookItem
     */
    @Override
    public void placeOrder(OrderPlaceRequest orderPlaceRequest) throws JsonProcessingException {
        double totalOrderValue = getTotalOrderValue(orderPlaceRequest.getBookList());
    }

    /**
     * Gets the total order value for a request
     * @param books list of books
     * @return total order value in case of issues in fetching the BookItem
     * @throws JsonProcessingException in case of issues in fetching the BookItem
     */
    private double getTotalOrderValue(final List<Book> books) throws JsonProcessingException {
        UUID orderId = UUID.randomUUID();
        double totalValue = 0;
        for(Book book : books){
            BookItem bookItem = getBookItemFromInventory(book);
            if(null == bookItem){
                throw new BookNotFoundException("Book"+ book.getBookName()+" not found in the inventory");
            }
            else{
                updateBookOrderTable(bookItem, orderId);
                totalValue += bookItem.getPrice()*book.getQuantity();
            }
        }
        updateOrderValue(orderId, totalValue);
        return totalValue;
    }

    /**
     * Gets the book stock details from inventory for provided book and author details
     * @param book DTO holding book details
     * @return book stock details
     * @throws JsonProcessingException in case of issues in fetching the BookItem
     */
    private BookItem getBookItemFromInventory(Book book) throws JsonProcessingException {
        /*BookItem bookItem = webClient.get()
                    .uri("/v1/inventory?bookName="+book.getBookName()+"&authorName="+book.getAuthorName())
                    .retrieve()
                    .bodyToMono(BookItem.class).block();*/
        String rawResponse = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/inventory")
                        .queryParam("bookName", book.getBookName())
                        .queryParam("authorName", book.getAuthorName())
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(rawResponse, BookItem.class);
    }

    /**
     * Updates book details in Book Order Table
     * @param bookItem book stock details
     * @param orderId represents orderID for the transaction
     */
    private void updateBookOrderTable(BookItem bookItem, UUID orderId){
        BookOrder bookOrder = BookOrder.builder()
                .orderId(orderId.toString())
                .bookName(bookItem.getBookName())
                        .build();
        bookOrderRepository.save(bookOrder);
    }

    /**
     * Updates total value order in the OrderValue Table
     * @param orderId represents order ID of the transaction
     * @param totalValue represents total value of the transaction
     */
    private void updateOrderValue(UUID orderId, double totalValue){
        OrderValue orderValue = OrderValue.builder().
                orderId(orderId.toString())
                .totalOrderValue(totalValue).build();
        orderValueRepository.save(orderValue);
    }
}
