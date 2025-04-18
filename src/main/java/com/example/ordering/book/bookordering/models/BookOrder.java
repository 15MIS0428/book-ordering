package com.example.ordering.book.bookordering.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Srivani Vaidya
 * Entity holding the mapping between book and order details
 */
@Entity
@Table(name = "book_orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookOrder {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "book_name")
    private String bookName;

}
