package mart.mono.purchases;

import mart.mono.cart.CartItemEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PurchasesServiceTest {

    @Mock
    PurchasesRepository purchasesRepository;

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    PurchasesService purchasesService;

    @Test
    void purchase() {
        List<CartItemEntity> cartItemEntities = List.of(CartItemEntity.builder().build());

        boolean result = purchasesService.purchase(cartItemEntities);

        Purchase purchase = new Purchase(UUID.randomUUID(), cartItemEntities);
        verify(purchasesRepository).save(any());
        verify(restTemplate).patchForObject("/api/products/{id}/decrement?quantity={quantity}", null, Void.class);
    }
}
