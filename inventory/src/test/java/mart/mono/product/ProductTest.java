package mart.mono.product;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ProductService mockProductService;

    @Test
    void products_list() throws Exception {

        Mockito.when(this.mockProductService.getForCatalog("electronics")).thenReturn(Collections.singletonList(Product.builder().build()));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products?catalog={catalog}", "electronics"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()", Matchers.greaterThan(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]", Matchers.hasKey("name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]", Matchers.hasKey("catalog")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void hits_purchaseApi() throws Exception {
        UUID id = UUID.randomUUID();

        mockMvc.perform(patch("/api/products/{0}/decrement?quantity=2", id))
            .andExpect(status().isOk());

        verify(mockProductService).decrementProductQuantity(id, 2);
    }

    @Test
    void getAll() {
        ProductRepository productRepository = Mockito.mock(ProductRepository.class);

        ProductService productService = new ProductService(productRepository);

        productService.getAll();

        Mockito.verify(productRepository).findAll();
    }
}
