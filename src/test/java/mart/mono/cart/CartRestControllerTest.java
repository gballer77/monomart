package mart.mono.cart;

import com.fasterxml.jackson.databind.ObjectMapper;
import mart.mono.MonomartApplication;
import mart.mono.cart.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartRestController.class)
@ContextConfiguration(classes = MonomartApplication.class)
class CartRestControllerTest {

  @MockBean
  CartService cartService;

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @Test
  @DisplayName("Should request cart item list when navigating to '/cart' ")
  void GetReturnsList() throws Exception {
    UUID uuid = UUID.randomUUID();
    List<CartItem> cartItems = List.of(CartItem
      .builder()
      .id(uuid)
      .build());
    when(cartService.get()).thenReturn(cartItems);

    mockMvc
      .perform(get("/api/cart"))
      .andExpect(jsonPath("$[0].id").value(uuid.toString()));
  }

  @Test
  @DisplayName("Should request to remove cart item when product id is given")
  void canRemoveItemFromCartById() throws Exception {
    UUID uuid = UUID.randomUUID();

    mockMvc
      .perform(put("/api/cart/{id}", uuid))
      .andExpect(status().isOk());

    verify(cartService, times(1)).remove(uuid);
    verify(cartService, times(1)).get();
  }

  @Test
  @DisplayName("Should request to checkout when navigating to '/checkout'")
  void canCheckout() throws Exception {
    mockMvc
      .perform(post("/api/cart/checkout"))
      .andExpect(status().isOk());

    verify(cartService, times(1)).checkOut();
  }

  @Nested
  @DisplayName("Add Items")
  class AddCartItemTest {

    @Test
    @DisplayName("Should request to add cart item when product is given")
    void canAddValidCartItem() throws Exception {
      UUID uuid = UUID.randomUUID();
      Product item = Product
        .builder()
        .id(uuid)
        .name("Action Jackson's Socks")
        .price("420.69")
        .build();
      String itemJson = objectMapper.writeValueAsString(item);
      when(cartService.add(item)).thenReturn(CartItem
        .builder()
        .product(item)
        .build());

      mockMvc
        .perform(post("/api/cart")
          .contentType(APPLICATION_JSON)
          .content(itemJson))
        .andExpect(status().isOk())
        .andExpect(jsonPath("product.name").value("Action Jackson's Socks"));

      verify(cartService, times(1)).add(item);
    }

    @Test
    @DisplayName("Should return 400 status when invalid product format is requested")
    void addInvalidCartItemThrows400() throws Exception {
      UUID uuid = UUID.randomUUID();
      Product item = Product
        .builder()
        .id(uuid)
        .name("Action Jackson's Socks")
        .price("420.69")
        .build();
      when(cartService.add(item)).thenReturn(CartItem
        .builder()
        .product(item)
        .build());

      mockMvc
        .perform(post("/api/cart")
          .contentType(APPLICATION_JSON)
          .content((byte[]) null))
        .andExpect(status().is4xxClientError());

      verify(cartService, times(0)).add(item);
    }
  }

}
