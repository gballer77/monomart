package mart.mono.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private List<CartItem> items;

    public double sumCost(){
        AtomicReference<Double> sum = new AtomicReference<>(0.0);
        items.forEach(cartItem -> sum.updateAndGet(v -> new Double((double) (v + Double.parseDouble(cartItem.getProduct().getPrice()) * cartItem.getQuantity()))));
        return sum.get();
    }
}
