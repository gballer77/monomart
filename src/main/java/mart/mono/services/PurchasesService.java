package mart.mono.services;

import mart.mono.models.CartItem;
import mart.mono.models.Purchase;
import mart.mono.product.services.ProductService;
import mart.mono.repositories.PurchasesRepository;
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
