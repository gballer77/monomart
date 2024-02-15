package mart.ports;

import java.util.List;

public interface PointOfSale {

    public boolean purchase(List<Purchaseable> purchaseables);
}
