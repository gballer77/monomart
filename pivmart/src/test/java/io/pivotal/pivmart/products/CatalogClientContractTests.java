package io.pivotal.pivmart.products;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = MOCK)
public class CatalogClientContractTests extends AbstractFakeNexusSetup {

    @Autowired
    private CatalogClient subject;

    @Test
    public void testCatalogClient() {
        fail("Add test to verify consumption of catalog contract");
    }

}
