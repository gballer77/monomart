package io.pivotal.pivmart.events;

import io.pivotal.pivmart.repositories.CartRepository;
import io.pivotal.pivmart.models.CartItem;
import io.pivotal.pivmart.models.Purchase;
import io.pivotal.pivmart.repositories.PurchasesRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PurchaseListener {
    private PurchasesRepository purchasesRepository;
    private CartRepository cartRepository;

    public PurchaseListener(CartRepository cartRepository, PurchasesRepository purchasesRepository) {
        this.cartRepository = cartRepository;
        this.purchasesRepository = purchasesRepository;
    }

    @EventListener(CheckoutEvent.class)
    public void onCheckOut(CheckoutEvent checkoutEvent) {
        List<CartItem> cart = checkoutEvent.getCart();
        Purchase purchase = Purchase.builder()
                .items(cart)
                .build();

        purchasesRepository.save(purchase);

        cartRepository.removeAll();
    }
}
