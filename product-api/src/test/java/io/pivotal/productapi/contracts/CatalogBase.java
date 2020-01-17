package io.pivotal.productapi.contracts;

import io.pivotal.productapi.Catalog;
import io.pivotal.productapi.CatalogEndpoint;
import io.pivotal.productapi.CatalogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.mockMvc;

@SpringBootTest
public abstract class CatalogBase {

    @Autowired
    private CatalogEndpoint controller;

    @MockBean
    private CatalogRepository mockCatalogRepository;

    @BeforeEach
    void setUp() {

        List<Catalog> fakeCatalog = new ArrayList<>();

        Catalog fakeCatalog1 = Catalog.builder()
                .id(UUID.fromString("f94ac11b-a492-4706-aa66-7f7fd47c23a4"))
                .catalogKey("clothes")
                .displayName("Clothes")
                .build();
        fakeCatalog.add(fakeCatalog1);

        Catalog fakeCatalog2 = Catalog.builder()
                .id(UUID.fromString("c882752b-6e81-4a13-bca5-cdb3fa04cecc"))
                .catalogKey("electronics")
                .displayName("Electronics")
                .build();
        fakeCatalog.add(fakeCatalog2);

        Catalog fakeCatalog3 = Catalog.builder()
                .id(UUID.fromString("1b607030-46a3-459d-afed-ed7694a518d7"))
                .catalogKey("sportinggoods")
                .displayName("Sporting Goods")
                .build();
        fakeCatalog.add(fakeCatalog3);

        Catalog fakeCatalog4 = Catalog.builder()
                .id(UUID.fromString("de7c7810-e120-4660-9b06-a414055ce28d"))
                .catalogKey("homegoods")
                .displayName("Home Goods")
                .build();
        fakeCatalog.add(fakeCatalog4);

        when( mockCatalogRepository.findAll() ).thenReturn( fakeCatalog );

        mockMvc(standaloneSetup(this.controller).build());
    }
}
