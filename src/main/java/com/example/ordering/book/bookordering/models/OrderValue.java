package com.example.ordering.book.bookordering.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author
 * Entity holding the information about total value of each order
 */
@Table(name = "orders")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderValue {

    @Id
    @Column(name = "order_id")
    private String orderId;

    @Column(name = "total_order_value")
    private double totalOrderValue;

}
