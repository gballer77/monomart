package io.pivotal.pivmart.database;

import io.pivotal.pivmart.products.Catalog;
import io.pivotal.pivmart.products.Product;
import io.pivotal.pivmart.purchases.Purchase;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class StaticDatabase {
    private Map<UUID, Catalog> catalogs = new HashMap<>();
    private Map<UUID, Product> products = new HashMap<>();
    private Map<UUID, CartItem> cart = new HashMap<>();
    private Map<UUID, Purchase> purchases = new HashMap<>();

    public StaticDatabase() {
        seed();
    }

    public Map<UUID, Catalog> getCatalogs() {
        return catalogs;
    }

    public Map<UUID, Product> getProducts() {
        return products;
    }

    public Map<UUID, CartItem> getCart() {
        return cart;
    }

    public Map<UUID, Purchase> getPurchases() {
        return purchases;
    }

    private UUID save(Catalog catalog) {
        catalogs.put(catalog.getId(), catalog);

        return catalog.getId();
    }

    private void save(Product product) {
        products.put(product.getId(), product);
    }

    public CartItem save(CartItem cartItem) {
        UUID uuid = UUID.randomUUID();
        cartItem.setId(uuid);
        cart.put(uuid, cartItem);

        return cartItem;
    }

    public void remove(CartItem cartItem) {
        cart.remove(cartItem.getId());
    }

    public void emptyCart() {
        cart.clear();
    }

    public Purchase save(Purchase purchase) {
        UUID uuid = UUID.randomUUID();
        purchase.setId(uuid);
        purchases.put(uuid, purchase);

        return purchase;
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
