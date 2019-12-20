package io.pivotal.pivmart.database;

import io.pivotal.pivmart.products.Product;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CartItem {
    private UUID id;
    private Product product;
    private Integer quantity;
}
