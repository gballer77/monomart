package io.pivotal.pivmart.database;

import io.pivotal.pivmart.catalog.Catalog;
import io.pivotal.pivmart.product.Product;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class StaticDatabase {
    private Map<UUID, Catalog> catalogs = new HashMap<>();
    private Map<UUID, Product> products = new HashMap<>();

    public StaticDatabase() {
        seed();
    }

    public Map<UUID, Catalog> getCatalogs() {
        return catalogs;
    }

    public Map<UUID, Product> getProducts() {
        return products;
    }

    private UUID save(Catalog catalog) {
        catalogs.put(catalog.getId(), catalog);

        return catalog.getId();
    }

    private void save(Product product) {
        products.put(product.getId(), product);
    }

    public void seed() {
        UUID homeGoods = save(Catalog.builder()
                .id(UUID.randomUUID())
                .name("Home Goods")
                .key("homegoods")
                .build());

        UUID sportingGoods = save(Catalog.builder()
                .id(UUID.randomUUID())
                .name("Sporting Goods")
                .key("sportinggoods")
                .build());

        UUID electronics = save(Catalog.builder()
                .id(UUID.randomUUID())
                .name("Electronics")
                .key("electronics")
                .build());

        UUID clothes = save(Catalog.builder()
                .id(UUID.randomUUID())
                .name("Clothes")
                .key("clothes")
                .build());

        save(Product.builder()
                .id(UUID.randomUUID())
                .catalogId(homeGoods)
                .name("Coffee Mug")
                .build());

        save(Product.builder()
                .id(UUID.randomUUID())
                .catalogId(homeGoods)
                .name("Toothpicks")
                .build());

        save(Product.builder()
                .id(UUID.randomUUID())
                .catalogId(homeGoods)
                .name("Umbrella")
                .build());

        save(Product.builder()
                .id(UUID.randomUUID())
                .catalogId(sportingGoods)
                .name("Basketball")
                .build());

        save(Product.builder()
                .id(UUID.randomUUID())
                .catalogId(sportingGoods)
                .name("Balaclava")
                .build());

        save(Product.builder()
                .id(UUID.randomUUID())
                .catalogId(electronics)
                .name("Apple iPad")
                .build());

        save(Product.builder()
                .id(UUID.randomUUID())
                .catalogId(electronics)
                .name("Nintendo Switch")
                .build());

        save(Product.builder()
                .id(UUID.randomUUID())
                .catalogId(clothes)
                .name("T-Shirt")
                .build());
    }
}
