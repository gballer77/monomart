package mart.mono.cart;

import mart.mono.cart.Product;
import mart.mono.purchases.PurchasesService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartService {
    private CartRepository cartRepository;
    private RestClient client;


    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
        this.client = RestClient.create();
    }

    public CartService(CartRepository cartRepository, RestClient restClient) {
        this.cartRepository = cartRepository;
        this.client = restClient;
    }

    public List<CartItem> get() {
        return cartRepository.findAll();
    }


    public CartItem add(Product product) {
        return cartRepository.save(CartItem.builder()
                .product(product)
                .quantity(1)
                .build());
    }

    public void remove(UUID cartItemId) {
        Optional<CartItem> cartItem = cartRepository.findById(cartItemId);
        cartItem.ifPresent(item -> cartRepository.delete(item));
    }

    public void checkOut() {
        List<CartItem> cart = cartRepository.findAll();
        boolean purchaseSuccess = Boolean.TRUE.equals(
                client.post()
                        .uri("http://localhost:8080/api/products")
                        .body(cart)
                        .retrieve()
                        .body(Boolean.class));
        if (purchaseSuccess) {
            cartRepository.deleteAll();
        }
    }
}
