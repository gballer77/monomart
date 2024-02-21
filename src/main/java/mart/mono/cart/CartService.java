package mart.mono.cart;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartService {
    private CartRepository cartRepository;
    private RestTemplate client;


    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
        this.client = new RestTemplate();
    }

    public List<CartItem> get() {
        return cartRepository.findAll().stream()
                .map(cartItemEntity -> cartItemEntity.toCartItem(client.getForObject(
                        "http://localhost:8080/api/products/{0}",
                        Product.class,
                        cartItemEntity.getProductId())))
                .toList();
    }


    public CartItem add(Product product) {
        return cartRepository.save(CartItemEntity.builder()
                .productId(product.getId())
                .quantity(1)
                .build()).toCartItem(product);
    }

    public void remove(UUID cartItemId) {
        Optional<CartItemEntity> cartItem = cartRepository.findById(cartItemId);
        cartItem.ifPresent(item -> cartRepository.delete(item));
    }

    public void checkOut() {
        List<CartItemEntity> cart = cartRepository.findAll();
        Boolean purchaseSuccess = client.postForObject(
                "http://localhost:8080/api/products",
                cart,
                Boolean.class);

        if (Boolean.TRUE.equals(purchaseSuccess)) {
            cartRepository.deleteAll();
        }
    }
}
