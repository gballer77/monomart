package mart.mono;

import mart.mono.catalog.Catalog;
import mart.mono.catalog.CatalogService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

@SpringBootTest
@AutoConfigureMockMvc
class CatalogTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private CatalogService mockCatalogService;

    @Test
    void catalogs_list() throws Exception {

        Mockito.when(this.mockCatalogService.getAll()).thenReturn(Collections.singletonList(Catalog.builder().build()));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/catalogs"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()", Matchers.greaterThanOrEqualTo(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]", Matchers.hasKey("id")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]", Matchers.hasKey("displayName")));
        }
}
