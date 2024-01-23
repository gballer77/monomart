package io.pivotal.pivmart.services;

import io.pivotal.pivmart.events.CartPublisher;
import io.pivotal.pivmart.models.CartItem;
import io.pivotal.pivmart.models.Product;
import io.pivotal.pivmart.repositories.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        Optional<CartItem> cartItem = cartRepository.findById(cartItemId);
        cartItem.ifPresent(item -> cartRepository.delete(item));
    }

    public void checkOut() {
        List<CartItem> cart = cartRepository.findAll();
        cartPublisher.publish(cart);
    }
}
