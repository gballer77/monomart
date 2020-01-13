package io.pivotal.pivmart.database;

import io.pivotal.pivmart.repositories.CartRepository;
import io.pivotal.pivmart.models.CartItem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class CartStaticDataRepository implements CartRepository {

    private StaticDatabase staticDatabase;

    public CartStaticDataRepository(StaticDatabase staticDatabase) {
        this.staticDatabase = staticDatabase;
    }

    @Override
    public List<CartItem> findAll() {
        return new ArrayList<>(staticDatabase.getCart().values());
    }

    @Override
    public CartItem find(UUID cartItemId) {
        return staticDatabase.getCart().get(cartItemId);
    }

    @Override
    public void removeAll() {
        staticDatabase.emptyCart();
    }

    @Override
    public CartItem save(CartItem cartItem) {
        return staticDatabase.save(cartItem);
    }

    @Override
    public void remove(CartItem cartItem) {
        staticDatabase.remove(cartItem);
    }
}
