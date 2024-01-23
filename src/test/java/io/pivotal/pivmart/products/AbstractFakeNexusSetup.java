package io.pivotal.pivmart.products;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerExtension;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;
import static org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode.REMOTE;

@ExtendWith({SpringExtension.class})
@SpringBootTest(
        webEnvironment = MOCK //,
//        properties = {
//                "eureka.client.enabled=false",
//                "spring.cloud.config.failFast=false",
//                "stubrunner.cloud.ribbon.enabled=false",
//                "stubrunner.idsToServiceIds.product-api=product-api"
//        }
)
@TestPropertySource(
        properties = {
                "stubrunner.idsToServiceIds.product-api=product-api"
        }
)
public abstract class AbstractFakeNexusSetup {

    static {
        System.setProperty("eureka.client.enabled", "false");
        System.setProperty("spring.cloud.config.failFast", "false");
    }

    @RegisterExtension
    static StubRunnerExtension stubRunnerExtension = new StubRunnerExtension()
            .stubsMode(REMOTE)
            .repoRoot(repoRoot())
            .downloadStub("io.pivotal", "product-api").withPort(8081)
            .withMappingsOutputFolder("build/outputmappings");


    protected static String repoRoot() {
        try {
            String userPath = System.getProperty("user.dir");
            userPath = userPath.substring(0, userPath.lastIndexOf('/'));
            userPath += "/nexus";
            File dir = new File(userPath);
            return "stubs://file://" + dir.toString();
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
            return "";
        }
    }

}
