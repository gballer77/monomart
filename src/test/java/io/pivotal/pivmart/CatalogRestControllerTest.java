package io.pivotal.pivmart;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasLength;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CatalogRestController.class)
class CatalogRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void canGetAListOfCatalogs() throws Exception {
        mockMvc.perform(get("/api/catalogs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Matchers.greaterThanOrEqualTo(1)))
                .andExpect(jsonPath("$[0]", hasKey("name")))
        ;
    }
}
