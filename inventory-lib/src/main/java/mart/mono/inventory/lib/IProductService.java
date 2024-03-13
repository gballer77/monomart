package mart.mono.inventory.lib;

import java.util.UUID;

public interface IProductService {
    void decrementProductQuantity(UUID productId, int quantity);
}
