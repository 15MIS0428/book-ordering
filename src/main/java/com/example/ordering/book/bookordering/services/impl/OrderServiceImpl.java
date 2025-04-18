package com.example.ordering.book.bookordering.services.impl;

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
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private WebClient webClient;

    @Autowired
    private OrderValueRepository orderValueRepository;

    @Autowired
    private BookOrderRepository bookOrderRepository;

    @Override
    public void placeOrder(OrderPlaceRequest orderPlaceRequest) throws JsonProcessingException {
        double totalOrderValue = getTotalOrderValue(orderPlaceRequest.getBookList());
    }

    private double getTotalOrderValue(final List<Book> books) throws JsonProcessingException {
        UUID orderId = UUID.randomUUID();
        double totalValue = 0;
        for(Book book : books){
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
            BookItem bookItem = objectMapper.readValue(rawResponse, BookItem.class);
            if(null == bookItem){

            }
            else{
                totalValue += bookItem.getPrice()*book.getQuantity();
            }
        }
        updateOrderValue(orderId, totalValue);
        return totalValue;
    }

    private void updateBookOrderTable(BookItem bookItem, UUID orderId){
        BookOrder bookOrder = BookOrder.builder()
                .orderId(orderId.toString())
                .bookName(bookItem.getBookName())
                        .build();
        bookOrderRepository.save(bookOrder);
    }

    private void updateOrderValue(UUID orderId, double totalValue){
        OrderValue orderValue = OrderValue.builder().
                orderId(orderId.toString())
                .totalOrderValue(totalValue).build();
        orderValueRepository.save(orderValue);
    }
}
