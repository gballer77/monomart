package mart.mono.purchases;

import mart.mono.cart.CartItem;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.UUID;

@Service
public class PurchasesService {

    private PurchasesRepository purchasesRepository;

    public PurchasesService(PurchasesRepository purchasesRepository) {
        this.purchasesRepository = purchasesRepository;
    }

    public List<Purchase> getAll() {
        return purchasesRepository.findAll();
    }

    public boolean purchase(List<CartItem> cartItems) {
        RestClient client = RestClient.create();

        try {
            purchasesRepository.save(new Purchase(UUID.randomUUID(), cartItems));
            cartItems.forEach(cartItem -> {
                RestClient.ResponseSpec responseSpec = client.post().uri("http://localhost:8080/api/products/{id}?quantity=-{quantity}", cartItem.getProduct().getId(), cartItem.getProduct().getQuantity())
                        .contentType(MediaType.APPLICATION_JSON)
                        .retrieve();
//                productService.decrementProductQuantity(cartItem.getProduct().getId(), cartItem.getQuantity());
            });
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
