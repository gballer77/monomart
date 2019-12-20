package io.pivotal.pivmart.products;

import io.pivotal.pivmart.products.Catalog;
import io.pivotal.pivmart.products.CatalogRestController;
import io.pivotal.pivmart.products.CatalogService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.hasKey;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CatalogRestController.class)
class CatalogRestControllerTest {

    @MockBean
    CatalogService catalogService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void list_returnsCatalogs() throws Exception {
        when(catalogService.getAll()).thenReturn(asList(Catalog.builder().build()));

        mockMvc.perform(get("/api/catalogs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Matchers.greaterThanOrEqualTo(1)))
                .andExpect(jsonPath("$[0]", hasKey("id")))
                .andExpect(jsonPath("$[0]", hasKey("catalogKey")))
                .andExpect(jsonPath("$[0]", hasKey("displayName")))
        ;

        verify(catalogService).getAll();
    }
}
