package mart.purchase;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mart.ports.Purchaseable;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "purchases")
public class Purchase {
    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @OneToMany
    private List<PurchasedItem> items;

    public double sumCost() {
        AtomicReference<Double> sum = new AtomicReference<>(0.0);
        items.forEach(purchasedItem -> sum.updateAndGet(v -> (v + Double.parseDouble(purchasedItem.getPrice()) * purchasedItem.getQuantity())));
        return sum.get();
    }

    public static Purchase ofPurchaseable(List<Purchaseable> purchaseables) {
        List<PurchasedItem> purchasedItemList = purchaseables.stream()
                .map(purchaseable -> PurchasedItem.builder()
                        .price(purchaseable.getPrice())
                        .quantity(purchaseable.getQuantity()).build())
                .toList();
        return Purchase.builder().items(purchasedItemList).build();
    }
}
