package mart.mono.purchases;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mart.mono.cart.CartItem;
import mart.mono.cart.CartItemEntity;
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
    private List<CartItemEntity> items;
//
//    public double sumCost(){
//        AtomicReference<Double> sum = new AtomicReference<>(0.0);
//        items.forEach(purchasedItem -> sum.updateAndGet(v -> new Double((double) (v + Double.parseDouble(purchasedItem.getProductId()) * purchasedItem.getQuantity()))));
//        return sum.get();
//    }
}
