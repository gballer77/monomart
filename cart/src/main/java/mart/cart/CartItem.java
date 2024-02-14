package mart.cart;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mart.ports.Purchaseable;
import mart.product.Product;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart_items")
public class CartItem implements Purchaseable {
    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @OneToOne
    @JoinColumn(name ="product_id", nullable = false)
    private Product product;

    private Integer quantity;


    @Override
    public UUID getProductId() {
        return product.getId();
    }

    @Override
    public String getPrice() {
        return product.getPrice();
    }
}
