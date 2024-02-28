package mart.mono.config;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ApiAdapter {
    private final RestTemplate restTemplate;
    private final RestClient restClient;

    public void decrementProductQuantity(UUID productId, Integer quantity) {
        restClient.patch()
                .uri("/api/products/{id}/decrement?quantity={quantity}", productId, quantity)
                .retrieve();
    }
}
