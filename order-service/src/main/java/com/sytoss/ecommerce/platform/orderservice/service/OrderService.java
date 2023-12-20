package com.sytoss.ecommerce.platform.orderservice.service;

import com.sytoss.ecommerce.platform.orderservice.model.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The OrderService interface provides methods for managing orders in the e-commerce platform.
 */
public interface OrderService {

    /**
     * Saves the provided order to the database.
     *
     * @param order The order to be saved.
     * @return The saved order.
     */
    Order saveOrder(Order order);

    /**
     * Retrieves a list of orders associated with the specified user email.
     *
     * @param userEmail The email of the user whose orders are to be retrieved.
     * @return A list of orders associated with the specified user email.
     */
    List<Order> getOrdersByUserEmail(String userEmail);

    /**
     * Retrieves the order with the specified UID.
     *
     * @param uid The UID of the order to be retrieved.
     * @return An {@link Optional} containing the order with the specified UID, or an empty Optional if not found.
     */
    Optional<Order> getOrderByUid(UUID uid);

    /**
     * Updates the existing order with information from the new order.
     *
     * @param existingOrder The existing order to be updated.
     * @param newOrder      The new order containing updated information.
     * @throws IllegalArgumentException If the existingOrder or newOrder is null.
     */
    void updateOrder(Order existingOrder, Order newOrder);
}

