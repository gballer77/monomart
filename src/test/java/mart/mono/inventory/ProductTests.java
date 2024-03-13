package mart.mono.inventory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class ProductTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    void getProductsOfCategoryContractTest() throws Exception {

        mockMvc.perform(get("/api/products?catalog={catalog}", "electronics"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()", equalTo(1)))
            .andExpect(jsonPath("$[0]", hasKey("id")))
            .andExpect(jsonPath("$[0]", hasKey("catalog")))
            .andExpect(jsonPath("$[0].catalog", hasKey("id")))
            .andExpect(jsonPath("$[0].catalog", hasKey("displayName")))
            .andExpect(jsonPath("$[0]", hasKey("name")))
            .andExpect(jsonPath("$[0]", hasKey("price")))
            .andExpect(jsonPath("$[0]", hasKey("description")))
            .andExpect(jsonPath("$[0]", hasKey("imageSrc")))
            .andExpect(jsonPath("$[0]", hasKey("imageAlt")))
            .andExpect(jsonPath("$[0]", hasKey("quantity")))
            .andDo(print());
    }

    @Test
    void getCatalogsContractTest() throws Exception {

        mockMvc.perform(get("/api/catalogs"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()", equalTo(1)))
            .andExpect(jsonPath("$[0]", hasKey("id")))
            .andExpect(jsonPath("$[0]", hasKey("displayName")))
        ;
    }

}
