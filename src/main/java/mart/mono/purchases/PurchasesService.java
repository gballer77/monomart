package mart.mono.purchases;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mart.mono.cart.CartItemEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PurchasesService {

    private final PurchasesRepository purchasesRepository;
    private final RestTemplate restTemplate;

    public List<Purchase> getAll() {
        return purchasesRepository.findAll();
    }

    public boolean purchase(List<CartItemEntity> cartItems) {
        try {
            purchasesRepository.save(new Purchase(UUID.randomUUID(), cartItems));
            cartItems.forEach(cartItem ->
                restTemplate.patchForObject("/api/products/{id}/decrement?quantity={quantity}", null, Void.class, cartItem.getProductId(), cartItem.getQuantity())
            );
            return true;
        } catch (Exception e) {
            log.info("Nope", e);
            return false;
        }
    }
}
