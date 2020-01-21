package io.pivotal.pivmart.products;

import io.pivotal.pivmart.models.Catalog;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// TODO: need to reference nexus relatively

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureStubRunner(
        stubsMode = StubRunnerProperties.StubsMode.REMOTE,
        ids = "io.pivotal:product-api:+:8081",
//        repositoryRoot = "stubs://file://../nexus/META-INF",
        repositoryRoot = "stubs://file://Users/danielfrey/Development/git/bryanneva/pivmart/nexus/META-INF",
        properties="stubs.find-producer=true"
)
@AutoConfigureJsonTesters
public class CatalogClientContractTests {

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
