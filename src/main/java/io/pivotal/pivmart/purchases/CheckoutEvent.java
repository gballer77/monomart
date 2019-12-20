package io.pivotal.pivmart.purchases;

import io.pivotal.pivmart.database.CartItem;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.ApplicationEvent;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class CheckoutEvent extends ApplicationEvent {

    private List<CartItem> cart;

    public CheckoutEvent(List<CartItem> cart) {
        super(cart);
        this.cart = cart;
    }
}
