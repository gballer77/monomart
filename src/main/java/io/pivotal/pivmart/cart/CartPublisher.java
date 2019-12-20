package io.pivotal.pivmart.cart;

import io.pivotal.pivmart.database.CartItem;
import io.pivotal.pivmart.purchases.CheckoutEvent;
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
