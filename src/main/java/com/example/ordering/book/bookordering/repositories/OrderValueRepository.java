package com.example.ordering.book.bookordering.repositories;

import com.example.ordering.book.bookordering.models.OrderValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderValueRepository extends JpaRepository<OrderValue, String> {
}
