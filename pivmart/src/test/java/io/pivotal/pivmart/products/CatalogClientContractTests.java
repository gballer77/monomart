package io.pivotal.pivmart.products;

import io.pivotal.pivmart.models.Catalog;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;

@ExtendWith({SpringExtension.class})
@SpringBootTest(webEnvironment = MOCK)
public class CatalogClientContractTests extends AbstractFakeNexusSetup {

    @Autowired
    private CatalogClient subject;

    @Test
    public void testCatalogClient() {

        List<Catalog> actual = subject.findAll();
        assertThat(actual)
                .isNotNull()
                .hasSize(4);

    }

}
