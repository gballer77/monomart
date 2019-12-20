package io.pivotal.pivmart.purchases;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;

import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PurchaseListenerIntegrationTest {
    @MockBean
    PurchaseListener purchaseListener;

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Test
    void onCheckOut() {
        CheckoutEvent event = new CheckoutEvent(Collections.emptyList());
        applicationEventPublisher.publishEvent(event);
        verify(purchaseListener).onCheckOut(event);
    }
}
