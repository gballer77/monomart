package mart.mono.shopping;

import mart.mono.services.CartService;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasKey;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class ShoppingTests {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    CartService cartService;

    private static JSONObject getProductObject() throws JSONException {
        JSONObject jsonPayload = new JSONObject();

        jsonPayload.put("id", "d5d6d7d8-9c8b-4d3c-9f2e-1d5c5d2c5d2a")
            .put("catalogId", "electronics")
            .put("name", "Dell Laptop")
            .put("imageSrc", "https://picsum.photos/id/2/200/300")
            .put("imageAlt", "Products Alt Text")
            .put("description", "This is a description of Dell Laptop")
            .put("price", "10.99")
            .put("quantity", 10);
        return jsonPayload;
    }

    @BeforeEach
    void clearCart() {
        cartService.removeAll();
    }

    private ResultActions addItemToCart() throws Exception {
        JSONObject jsonPayload = getProductObject();

        MockHttpServletRequestBuilder request = post("/api/cart")
            .contentType(APPLICATION_JSON)
            .content(jsonPayload.toString());

        return mockMvc.perform(request);
    }

    @Test
    void removeItemFromCartContractTest() throws Exception {
        ResultActions result = addItemToCart();
        JSONObject resultJson = new JSONObject(result.andReturn().getResponse().getContentAsString());

        String uuid = resultJson.getString("id");

        mockMvc.perform(put("/api/cart/{id}", uuid))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()", equalTo(0)));
    }

    @Test
    void addToCartContractTest() throws Exception {
        ResultActions resultActions = addItemToCart();
        resultActions.andExpect(status().isOk())
            .andExpect(jsonPath("$", hasKey("product")))
            .andExpect(jsonPath("$", hasKey("id")))
            .andExpect(jsonPath("$", hasKey("quantity")))
            .andDo(print());
    }

    @Test
    void getCartContractTest() throws Exception {
        addItemToCart();

        mockMvc.perform(get("/api/cart"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()", greaterThanOrEqualTo(1)))
            .andExpect(jsonPath("$[0]", hasKey("product")))
            .andExpect(jsonPath("$[0]", hasKey("id")))
            .andExpect(jsonPath("$[0]", hasKey("quantity")))
            .andDo(print())
        ;
    }


    @Test
    void checkoutCartContractTest() throws Exception {
        mockMvc.perform(post("/api/cart/checkout"))
            .andExpect(status().isOk());
    }
}