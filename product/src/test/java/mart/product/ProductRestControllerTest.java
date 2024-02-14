package mart.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasKey;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ProductRestControllerTest {

    MockMvc mockMvc;

    @Mock
    ProductService mockProductService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ProductRestController(mockProductService)).build();
    }

    @Test
    void products_list() throws Exception {

        when(mockProductService.getForCatalog("electronics")).thenReturn(singletonList(Product.builder().build()));

        mockMvc.perform(get("/api/products?catalog={catalog}", "electronics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThan(0)))
                .andExpect(jsonPath("$[0]", hasKey("id")))
                .andExpect(jsonPath("$[0]", hasKey("catalog")))
                .andExpect(jsonPath("$[0]", hasKey("name")))
                .andExpect(jsonPath("$[0]", hasKey("price")))
                .andExpect(jsonPath("$[0]", hasKey("description")))
                .andExpect(jsonPath("$[0]", hasKey("imageSrc")))
                .andExpect(jsonPath("$[0]", hasKey("imageAlt")))
                .andExpect(jsonPath("$[0]", hasKey("quantity")))
                .andDo(print());
    }

    @Test
    void whenProductsRestControllerCalledWithoutCatalogThenReturnAllProducts() throws Exception {

        when(mockProductService.getAll()).thenReturn(List.of(Product.builder().build(), Product.builder().build()));

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThan(0)))
                .andDo(print())
        ;
    }
}