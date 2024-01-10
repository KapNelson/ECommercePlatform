package com.sytoss.ecommerce.platform.fulfillmentservice.statemachine.event;

public enum OrderEvent {
    ALLOCATE_INVENTORY,
    CREATE_SHIPPING,
    SHIPPING_SUCCEED,
    PROCESSING_FAILED
}