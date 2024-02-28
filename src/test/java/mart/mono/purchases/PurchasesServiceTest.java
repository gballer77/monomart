package mart.mono.purchases;

import mart.mono.MonomartApplication;
import mart.mono.cart.CartItemEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@ExtendWith(MockitoExtension.class)
class PurchasesServiceTest {

    @Mock
    PurchasesRepository purchasesRepository;

    @Mock
    RestClient restClient;


    @InjectMocks
    PurchasesService purchasesService;

    @Test
    void iunno() {
        List<CartItemEntity> cartItemEntities = List.of(CartItemEntity.builder().build());
        Purchase purchase = new Purchase(UUID.randomUUID(), cartItemEntities);
    when(purchasesRepository.save(any())).thenReturn(true);
    when(restClient.patch())

    }


}
