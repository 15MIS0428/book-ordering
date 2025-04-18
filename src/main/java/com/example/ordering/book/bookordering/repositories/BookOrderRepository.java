package com.example.ordering.book.bookordering.repositories;

import com.example.ordering.book.bookordering.models.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Srivani Vaidya
 * Repository class to handle CRUD operations for BookOrder entity
 */
@Repository
public interface BookOrderRepository extends JpaRepository<BookOrder, Long> {
}
