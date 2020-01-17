package io.pivotal.productapi.contracts;

import io.pivotal.productapi.CatalogEndpoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.mockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public abstract class CatalogBase {

    @Autowired
    private CatalogEndpoint controller;

    @BeforeEach
    void setUp() {
        mockMvc(standaloneSetup(this.controller).build());
    }
}
