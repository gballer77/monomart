package io.pivotal.pivmart.products;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.fail;

public class CatalogClientContractTests extends AbstractFakeNexusSetup {

    @Autowired
    private CatalogClient subject;

    @Test
    public void testCatalogClient() {
        fail("Add test to verify consumption of catalog contract");
    }

}
