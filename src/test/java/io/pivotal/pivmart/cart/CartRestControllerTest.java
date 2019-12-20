package io.pivotal.pivmart.cart;

import io.pivotal.pivmart.database.CartItem;
import io.pivotal.pivmart.products.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.UUID;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CartRestController.class)
class CartRestControllerTest {

    @MockBean
    CartService cartService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void list() throws Exception {
        when(cartService.get()).thenReturn(asList(CartItem.builder().build()));

        mockMvc.perform(get("/api/cart"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThanOrEqualTo(1)))
                .andExpect(jsonPath("$[0]", hasKey("product")))
        ;

        verify(cartService).get();
    }

    @Test
    void add() throws Exception {
        when(cartService.add(any()))
                .thenReturn(CartItem.builder().build());

        UUID id = UUID.randomUUID();
        UUID catalogId = UUID.randomUUID();

        //language=JSON
        String requestBody = "{\n" +
                "  \"id\": \"" + id + "\",\n" +
                "  \"catalogId\": \"" + catalogId + "\",\n" +
                "  \"name\": \"name\",\n" +
                "  \"price\": \"19.99\",\n" +
                "  \"description\": \"description\",\n" +
                "  \"imageSrc\": \"imageSrc\",\n" +
                "  \"imageAlt\": \"imageAlt\"\n" +
                "}";

        MockHttpServletRequestBuilder request = post("/api/cart")
                .contentType(APPLICATION_JSON)
                .content(requestBody);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasKey("product")))
        ;

        verify(cartService).add(Product.builder()
                .id(id)
                .catalogId(catalogId)
                .name("name")
                .price("19.99")
                .description("description")
                .imageSrc("imageSrc")
                .imageAlt("imageAlt")
                .build());
    }

    @Test
    void remove() throws Exception {
        when(cartService.get()).thenReturn(asList(CartItem.builder().build()));

        UUID expectedUuid = UUID.randomUUID();

        mockMvc.perform(put("/api/cart/{id}", expectedUuid))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThan(0)));

        verify(cartService).remove(expectedUuid);
    }

    @Test
    void checkOut() throws Exception {
        mockMvc.perform(post("/api/cart/checkout"))
                .andExpect(status().isOk());

        verify(cartService).checkOut();
    }
}
