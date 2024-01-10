package com.sytoss.ecommerce.platform.fulfillmentservice.statemachine.config;

import com.sytoss.ecommerce.platform.fulfillmentservice.statemachine.action.AllocateInventoryAction;
import com.sytoss.ecommerce.platform.fulfillmentservice.statemachine.action.CreateShippingAction;
import com.sytoss.ecommerce.platform.fulfillmentservice.statemachine.action.ProcessingFailedAction;
import com.sytoss.ecommerce.platform.fulfillmentservice.statemachine.action.ShippingSucceedAction;
import com.sytoss.ecommerce.platform.fulfillmentservice.statemachine.event.OrderEvent;
import com.sytoss.ecommerce.platform.fulfillmentservice.statemachine.state.OrderState;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@AllArgsConstructor
@Configuration
@EnableStateMachineFactory
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderState, OrderEvent> {
    private final AllocateInventoryAction allocateInventoryAction;
    private final CreateShippingAction createShippingAction;
    private final ShippingSucceedAction shippingSucceedAction;
    private final ProcessingFailedAction processingFailedAction;

    @Override
    public void configure(StateMachineConfigurationConfigurer<OrderState, OrderEvent> config)
            throws Exception {
        config
                .withConfiguration()
                .autoStartup(true);
    }

    @Override
    public void configure(StateMachineStateConfigurer<OrderState, OrderEvent> states)
            throws Exception {
        states
                .withStates()
                .initial(OrderState.NEW)
                .end(OrderState.DONE)
                .states(EnumSet.allOf(OrderState.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderState, OrderEvent> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(OrderState.NEW).target(OrderState.INVENTORY_ALLOCATION).event(OrderEvent.ALLOCATE_INVENTORY)
                .action(allocateInventoryAction)

                .and()
                .withExternal()
                .source(OrderState.INVENTORY_ALLOCATION).target(OrderState.SHIPPING_CREATION).event(OrderEvent.CREATE_SHIPPING)
                .action(createShippingAction)

                .and()
                .withExternal()
                .source(OrderState.SHIPPING_CREATION).target(OrderState.DONE).event(OrderEvent.SHIPPING_SUCCEED)
                .action(shippingSucceedAction)

                .and()
                .withExternal()
                .source(OrderState.INVENTORY_ALLOCATION).target(OrderState.FAILED).event(OrderEvent.PROCESSING_FAILED)
                .action(processingFailedAction)

                .and()
                .withExternal()
                .source(OrderState.SHIPPING_CREATION).target(OrderState.FAILED).event(OrderEvent.PROCESSING_FAILED)
                .action(processingFailedAction);
    }
}
