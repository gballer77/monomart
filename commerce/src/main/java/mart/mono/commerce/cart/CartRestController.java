package mart.mono.commerce.cart;

import mart.mono.commerce.product.Product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class CartRestController {

    private final CartService cartService;

    public CartRestController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("api/v1/cart")
    public List<CartItem> list() {
        return cartService.get();
    }

    //TODO delete after April 1 2024
    @PostMapping("api/v1/cart")
    public CartItem add(@RequestBody mart.mono.inventory.lib.Product product) {
        return cartService.add(product);
    }

    @PostMapping("api/v2/cart")
    public CartItem add(@RequestBody Product product) {
        return cartService.add(product);
    }

    @PutMapping("api/v1/cart/{id}")
    public List<CartItem> remove(@PathVariable UUID id) {
        cartService.remove(id);
        return cartService.get();
    }

    @PostMapping("api/v1/cart/checkout")
    public ResponseEntity checkOut() {
        cartService.checkOut();
        return ResponseEntity.ok().build();
    }
}
