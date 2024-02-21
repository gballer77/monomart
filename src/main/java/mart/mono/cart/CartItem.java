package mart.mono.cart;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mart.mono.cart.Product;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private Product product;

    private Integer quantity;
}
