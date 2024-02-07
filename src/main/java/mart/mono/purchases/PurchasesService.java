package mart.mono.purchases;

import mart.mono.cart.CartItem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PurchasesService {

    private PurchasesRepository purchasesRepository;

    private ProductService productService;

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
