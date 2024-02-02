package mart.mono.cart;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import mart.mono.product.Product;
import mart.mono.purchases.PurchasesService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(CartService.class)
class CartServiceTest {

  @MockBean
  CartRepository cartRepository;

  // STRANGER DANGER!!!
  @MockBean
  PurchasesService purchasesService;

  @Autowired
  CartService cartService;

  @Captor
  ArgumentCaptor<CartItem> cartItem;

  @Test
  @DisplayName("Should give list of all cart items")
  void returnListOfCartItems() {
    List<CartItem> cartItems = List.of(CartItem
      .builder()
      .quantity(69)
      .build());
    when(cartRepository.findAll()).thenReturn(cartItems);

    List<CartItem> returnedItems = cartService.get();

    assertEquals(cartItems, returnedItems);
    verify(cartRepository, times(1)).findAll();
  }

  @Test
  @DisplayName("Should add single item to cart when given product")
  void addSingleProduct() {
    Product product = Product
      .builder()
      .name("GBaller's Secret Pokemon Card Collection")
      .build();

    cartService.add(product);
    verify(cartRepository).save(cartItem.capture());
    CartItem cartItemResolvedValue = cartItem.getValue();

    assertEquals("GBaller's Secret Pokemon Card Collection", cartItemResolvedValue
      .getProduct()
      .getName());
    assertEquals(1, cartItemResolvedValue.getQuantity());
  }

  @Nested
  @DisplayName("Remove item from cart")
  class removeItem {

    @Test
    @DisplayName(" Should remove item from cart when given item id")
    void removeSuccess() {
      UUID uuid = UUID.randomUUID();
      when(cartRepository.findById(uuid))
        .thenReturn(Optional.of(CartItem
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
    @DisplayName("Should request to checkout items in cart")
    void checkOutRequest() {
      List<CartItem> items = List.of(CartItem
        .builder()
        .id(UUID.randomUUID())
        .build());
      when(cartRepository.findAll()).thenReturn(items);

      cartService.checkOut();

      verify(purchasesService).purchase(items);
    }
    @Test
    @DisplayName("Should remove all items in cart when purchase success")
    void checkOutSuccess() {
      when(purchasesService.purchase(any())).thenReturn(true);

      cartService.checkOut();

      verify(cartRepository, times(1)).deleteAll();
    }
    @Test
    @DisplayName("Should not remove items in cart when purchase fails")
    void checkOutFail() {
      when(purchasesService.purchase(any())).thenReturn(false);

      cartService.checkOut();

      verify(cartRepository, times(0)).deleteAll();
    }
  }
}





