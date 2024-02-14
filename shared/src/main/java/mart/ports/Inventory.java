package mart.ports;

import java.util.UUID;

public interface Inventory {

    boolean decrementProductQuantity(UUID productId, int quantity);
}
