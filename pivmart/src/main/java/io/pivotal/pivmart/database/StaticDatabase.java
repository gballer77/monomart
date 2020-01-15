package io.pivotal.pivmart.database;

import io.pivotal.pivmart.models.CartItem;
import io.pivotal.pivmart.models.Catalog;
import io.pivotal.pivmart.models.Product;
import io.pivotal.pivmart.models.Purchase;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class StaticDatabase {
    private Map<UUID, CartItem> cart = new HashMap<>();
    private Map<UUID, Purchase> purchases = new HashMap<>();

    public Map<UUID, CartItem> getCart() {
        return cart;
    }

    public Map<UUID, Purchase> getPurchases() {
        return purchases;
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
}
