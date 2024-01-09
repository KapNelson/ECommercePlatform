package com.sytoss.ecommerce.platform.fulfillmentservice.service;

import com.sytoss.ecommerce.platform.fulfillmentservice.model.order.Order;

/**
 * Service responsible for handling fulfillment processes for orders.
 */
public interface FulfillmentService {

    /**
     * Initiates the fulfillment process for the given order.
     *
     * @param order The order for which the fulfillment process is initiated.
     */
    void startFulfillment(Order order);
}
