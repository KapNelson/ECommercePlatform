package com.sytoss.ecommerce.platform.orderservice.repository;

import com.sytoss.ecommerce.platform.orderservice.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends MongoRepository<Order, UUID> {
    List<Order> findByUserEmail(String userEmail);
}
