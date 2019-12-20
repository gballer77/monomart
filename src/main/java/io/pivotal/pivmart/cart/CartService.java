package io.pivotal.pivmart.cart;

import io.pivotal.pivmart.database.CartItem;
import io.pivotal.pivmart.products.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CartService {
    private CartRepository cartRepository;
    private CartPublisher cartPublisher;

    public CartService(CartRepository cartRepository, CartPublisher cartPublisher) {
        this.cartRepository = cartRepository;
        this.cartPublisher = cartPublisher;
    }

    public List<CartItem> get() {
        return cartRepository.findAll();
    }

    public CartItem add(Product product) {
        return cartRepository.save(CartItem.builder()
                .product(product)
                .quantity(1)
                .build());
    }

    public void remove(UUID cartItemId) {
        CartItem cartItem = cartRepository.find(cartItemId);
        cartRepository.remove(cartItem);
    }

    public void checkOut() {
        List<CartItem> cart = cartRepository.findAll();
        cartPublisher.publish(cart);
    }
}
