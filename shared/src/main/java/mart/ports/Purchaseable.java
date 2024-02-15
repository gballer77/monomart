package mart.ports;

import java.util.UUID;

public interface Purchaseable {

    public UUID getProductId();

    public Integer getQuantity();

    public String getPrice();
}
