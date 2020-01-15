package io.pivotal.productapi.database;

import io.pivotal.productapi.Catalog;
import io.pivotal.productapi.Product;
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
        UUID homeGoods = save(CatalogFactory.create("Home Goods"));
        UUID sportingGoods = save(CatalogFactory.create("Sporting Goods"));
        UUID electronics = save(CatalogFactory.create("Electronics"));
        UUID clothes = save(CatalogFactory.create("Clothes"));
        save(ProductFactory.create(homeGoods, "Coffee Mug"));
        save(ProductFactory.create(homeGoods, "Toothpicks"));
        save(ProductFactory.create(homeGoods, "Umbrella"));
        save(ProductFactory.create(sportingGoods, "Basketball"));
        save(ProductFactory.create(sportingGoods, "Balaclava"));
        save(ProductFactory.create(electronics, "Apple iPad"));
        save(ProductFactory.create(electronics, "Nintendo Switch"));
        save(ProductFactory.create(clothes, "T-Shirt"));

    }
}
