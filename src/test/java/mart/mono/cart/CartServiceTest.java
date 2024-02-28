package mart.mono.cart;

import mart.mono.MonomartApplication;
import mart.mono.purchases.PurchasesService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    CartRepository cartRepository;

    @Mock
    RestTemplate mockRestTemplate;

    @Mock
    PurchasesService purchasesService;

    @InjectMocks
    CartService cartService;

    @Captor
    ArgumentCaptor<CartItemEntity> cartItem;

    @Test
    @DisplayName("Should give list of all cart items")
    void returnListOfCartItems() {
        List<CartItemEntity> cartItemEntities = List.of(CartItemEntity
                .builder()
                .quantity(69)
                .build());
        when(cartRepository.findAll()).thenReturn(cartItemEntities);

        List<CartItem> returnedItems = cartService.get();

        assertEquals(cartItemEntities.stream().map(cartItemEntity -> cartItemEntity.toCartItem(null)).toList(), returnedItems);
        verify(cartRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should add single item to cart when given product")
    void addSingleProduct() {
        UUID id = UUID.randomUUID();
        Product product = Product
                .builder()
                .name("GBaller's Secret Pokemon Card Collection")
                .id(id)
                .build();
        when(cartRepository.save(cartItem.capture())).thenReturn(CartItemEntity.builder().build());
        cartService.add(product);
        CartItemEntity cartItemEntityResolvedValue = cartItem.getValue();

        assertEquals(id, cartItemEntityResolvedValue.getProductId());
        assertEquals(1, cartItemEntityResolvedValue.getQuantity());
    }

    @Nested
    @DisplayName("Remove item from cart")
    class removeItem {

        @Test
        @DisplayName(" Should remove item from cart when given item id")
        void removeSuccess() {
            UUID uuid = UUID.randomUUID();
            when(cartRepository.findById(uuid))
                    .thenReturn(Optional.of(CartItemEntity
                            .builder()
                            .id(uuid)
                            .build()));

            cartService.remove(uuid);

            verify(cartRepository, times(1)).delete(any());
        }

        @Test
        @DisplayName("Should not try to remove item from cart when given id cannot be found")
        void removeFail() {
            UUID uuid = UUID.randomUUID();
            when(cartRepository.findById(uuid))
                    .thenReturn(Optional.empty());

            cartService.remove(uuid);

            verify(cartRepository, times(0)).delete(any());
        }
    }

    @Nested
    @DisplayName("Request Checkout")
    class checkout {
        @Test
        @DisplayName("Should remove all items in cart when purchase success")
        void checkOutRequest() {
            List<CartItemEntity> items = List.of(CartItemEntity
                    .builder()
                    .id(UUID.randomUUID())
                    .build());

            when(cartRepository.findAll()).thenReturn(items);
            when (purchasesService.purchase(items)).thenReturn(true);
            cartService.checkOut();

            verify(cartRepository, times(1)).deleteAll();
        }


        @Test
        @DisplayName("Should not remove items in cart when purchase fails")
        void checkOutFail() {
            List<CartItemEntity> items = List.of(CartItemEntity
                    .builder()
                    .id(UUID.randomUUID())
                    .build());

            when(cartRepository.findAll()).thenReturn(items);
            when (purchasesService.purchase(items)).thenReturn(false);


            cartService.checkOut();

            verify(cartRepository, times(0)).deleteAll();
        }
    }
}





