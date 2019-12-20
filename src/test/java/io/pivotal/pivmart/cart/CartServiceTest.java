package io.pivotal.pivmart.cart;

import io.pivotal.pivmart.database.CartItem;
import io.pivotal.pivmart.products.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    CartRepository cartRepository;

    @Mock
    CartPublisher cartPublisher;

    @InjectMocks
    CartService cartService;

    @Captor
    ArgumentCaptor<CartItem> cartItemArgumentCaptor;

    @Test
    public void get() {
        List<CartItem> expected = Collections.singletonList(CartItem.builder().build());
        when(cartRepository.findAll()).thenReturn(expected);

        assertThat(cartService.get()).isEqualTo(expected);
    }

    @Test
    public void add() {
        UUID uuid = UUID.randomUUID();

        Product product = Product.builder()
                .id(uuid)
                .build();

        cartService.add(product);

        verify(cartRepository).save(cartItemArgumentCaptor.capture());

        CartItem actual = cartItemArgumentCaptor.getValue();
        assertThat(actual.getProduct().getId()).isEqualTo(uuid);
    }

    @Test
    public void remove() {
        UUID uuid = UUID.randomUUID();
        when(cartRepository.find(uuid)).thenReturn(CartItem.builder().id(uuid).build());

        cartService.remove(uuid);

        verify(cartRepository).remove(cartItemArgumentCaptor.capture());

        CartItem actual = cartItemArgumentCaptor.getValue();
        assertThat(actual.getId()).isEqualTo(uuid);
    }

    @Test
    public void checkOut() {
        cartService.checkOut();
        verify(cartRepository).findAll();
        verify(cartPublisher).publish(any());
    }
}
