package mart.cart;

import mart.ports.PointOfSale;
import mart.ports.Purchaseable;
import mart.product.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartService {
    private CartRepository cartRepository;

    private PointOfSale pointOfSale;

    public CartService(CartRepository cartRepository, PointOfSale pointOfSale) {
        this.cartRepository = cartRepository;
        this.pointOfSale = pointOfSale;
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
        List<Purchaseable> cart = new ArrayList<>(cartRepository.findAll());
        boolean purchaseSuccess = pointOfSale.purchase(cart);
        if (purchaseSuccess) {
            cartRepository.deleteAll();
        }
    }
}
