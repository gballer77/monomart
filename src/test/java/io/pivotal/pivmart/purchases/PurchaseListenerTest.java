package io.pivotal.pivmart.purchases;

import io.pivotal.pivmart.cart.CartRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PurchaseListenerTest {
    @Mock
    PurchasesRepository purchasesRepository;

    @Mock
    CartRepository cartRepository;

    @InjectMocks
    PurchaseListener purchaseListener;

    @Test
    public void onCheckOut() {
        purchaseListener.onCheckOut(new CheckoutEvent(Collections.emptyList()));
        verify(purchasesRepository).save(any());
        verify(cartRepository).removeAll();
    }
}
