package mart.mono.commerce.cart;

import mart.mono.commerce.product.Product;
import mart.mono.commerce.purchase.PurchasesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartService {
    private final CartRepository cartRepository;

    private final PurchasesService purchasesService;

    public CartService(CartRepository cartRepository, PurchasesService purchasesService) {
        this.cartRepository = cartRepository;
        this.purchasesService = purchasesService;
    }

    public List<CartItem> get() {
        return cartRepository.findAll();
    }

    // TODO Delete after April 1 2024
    public CartItem add(mart.mono.inventory.lib.Product product) {
        Product cartProduct = new Product(product.getId(), product.getName(), product.getPrice());

        return cartRepository.save(CartItem.builder()
            .product(cartProduct)
            .quantity(1)
            .build());

    }

    public CartItem add(Product product) {
        return cartRepository.save(CartItem.builder()
                .product(product)
                .quantity(1)
                .build());
    }

    public void remove(UUID cartItemId) {
        Optional<CartItem> cartItem = cartRepository.findById(cartItemId);
        cartItem.ifPresent(cartRepository::delete);
    }

    public void removeAll() {
        cartRepository.deleteAll();
    }

    public void checkOut() {
        List<CartItem> cart = cartRepository.findAll();
        boolean purchaseSuccess = purchasesService.purchase(cart);
        if (purchaseSuccess) {
            cartRepository.deleteAll();
        }
    }
}
