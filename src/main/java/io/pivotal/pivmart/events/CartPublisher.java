package io.pivotal.pivmart.events;

import io.pivotal.pivmart.models.CartItem;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartPublisher {
    private ApplicationEventPublisher applicationEventPublisher;

    public CartPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publish(List<CartItem> cart) {
        CheckoutEvent checkoutEvent = new CheckoutEvent(cart);
        applicationEventPublisher.publishEvent(checkoutEvent);
    }
}
