package io.pivotal.pivmart;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StaticDataProductRepository implements ProductRepository {
    List<Product> products = new ArrayList<>();

    public StaticDataProductRepository() {
        products.add(Product.builder()
                .name("a")
                .catalog("a")
                .build());
        products.add(Product.builder()
                .name("b")
                .catalog("b")
                .build());
        products.add(Product.builder()
                .name("c")
                .catalog("c")
                .build());
    }

    @Override
    public List<Product> findAllByCatalog(String anyString) {
        return products;
    }
}
