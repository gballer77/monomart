package mart.purchase;

import mart.ports.Inventory;
import mart.ports.PointOfSale;
import mart.ports.Purchaseable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchasesService implements PointOfSale {

    private PurchasesRepository purchasesRepository;

    private Inventory inventory;

    public PurchasesService(PurchasesRepository purchasesRepository, Inventory inventory) {
        this.purchasesRepository = purchasesRepository;
        this.inventory = inventory;
    }

    public List<Purchase> getAll() {
        return purchasesRepository.findAll();
    }

    public boolean purchase(List<Purchaseable> cartItems) {
        try {
            purchasesRepository.save(Purchase.ofPurchaseable(cartItems));
            cartItems.forEach(cartItem -> {
                inventory.decrementProductQuantity(cartItem.getProductId(), cartItem.getQuantity());
            });
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
