package mart.mono.product;

import mart.mono.product.Product;
import mart.mono.product.ProductRepository;
import mart.mono.product.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasKey;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

        when(this.mockProductService.getForCatalog("electronics")).thenReturn(singletonList(Product.builder().build()));

        mockMvc.perform(get("/api/products?catalog={catalog}", "electronics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThan(0)))
                .andExpect(jsonPath("$[0]", hasKey("name")))
                .andExpect(jsonPath("$[0]", hasKey("catalogId")))
                .andDo(print());
    }

    @Test
    void getAll() {
        ProductRepository productRepository = mock(ProductRepository.class);

        ProductService productService = new ProductService(productRepository);

        productService.getAll();

        verify(productRepository).findAll();
    }
}
