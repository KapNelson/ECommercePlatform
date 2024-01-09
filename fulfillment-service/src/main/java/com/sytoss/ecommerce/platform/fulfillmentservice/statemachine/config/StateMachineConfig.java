package com.sytoss.ecommerce.platform.fulfillmentservice.statemachine.config;

import com.sytoss.ecommerce.platform.fulfillmentservice.statemachine.event.FulfillmentEvent;
import com.sytoss.ecommerce.platform.fulfillmentservice.statemachine.state.FulfillmentState;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;

@Configuration
@EnableStateMachine
public class StateMachineConfig
        extends EnumStateMachineConfigurerAdapter<FulfillmentState, FulfillmentEvent> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<FulfillmentState, FulfillmentEvent> config)
            throws Exception {
        config
                .withConfiguration()
                .autoStartup(true)
                .listener(listener());
    }

    @Override
    public void configure(StateMachineStateConfigurer<FulfillmentState, FulfillmentEvent> states)
            throws Exception {
        states
                .withStates()
                .initial(FulfillmentState.NEW)
                .end(FulfillmentState.DONE)
                .states(EnumSet.allOf(FulfillmentState.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<FulfillmentState, FulfillmentEvent> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(FulfillmentState.NEW).target(FulfillmentState.INVENTORY_ALLOCATION).event(FulfillmentEvent.ALLOCATE_INVENTORY)
                .and()
                .withExternal()
                .source(FulfillmentState.INVENTORY_ALLOCATION).target(FulfillmentState.SHIPPING_CREATION).event(FulfillmentEvent.CREATE_SHIPPING)
                .and()
                .withExternal()
                .source(FulfillmentState.SHIPPING_CREATION).target(FulfillmentState.DONE).event(FulfillmentEvent.SHIPPING_SUCCEED);
    }

    @Bean
    public StateMachineListener<FulfillmentState, FulfillmentEvent> listener() {
        return new StateMachineListenerAdapter<FulfillmentState, FulfillmentEvent>() {
            @Override
            public void stateChanged(State<FulfillmentState, FulfillmentEvent> from, State<FulfillmentState, FulfillmentEvent> to) {
                System.out.println("State change to " + to.getId());
            }
        };
    }
}
