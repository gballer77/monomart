package mart.mono.commerce.purchase;

import mart.mono.commerce.cart.CartItem;
import mart.mono.inventory.product.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PurchasesService {

    private final PurchasesRepository purchasesRepository;

    private final ProductService productService;

    public PurchasesService(PurchasesRepository purchasesRepository, ProductService productService) {
        this.purchasesRepository = purchasesRepository;
        this.productService = productService;
    }

    public List<Purchase> getAll() {
        return purchasesRepository.findAll();
    }

    public boolean purchase(List<CartItem> cartItems) {
        try {
            purchasesRepository.save(new Purchase(UUID.randomUUID(), cartItems));
            cartItems.forEach(cartItem -> {
                productService.decrementProductQuantity(cartItem.getProduct().getId(), cartItem.getQuantity());
            });
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
