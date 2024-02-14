package mart.mono.services;

import mart.mono.models.CartItem;
import mart.mono.repositories.CartRepository;
import mart.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartService {
    private CartRepository cartRepository;

    private PurchasesService purchasesService;

    public CartService(CartRepository cartRepository, PurchasesService purchasesService) {
        this.cartRepository = cartRepository;
        this.purchasesService = purchasesService;
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
        boolean purchaseSuccess = purchasesService.purchase(cart);
        if (purchaseSuccess) {
            cartRepository.deleteAll();
        }
    }
}
