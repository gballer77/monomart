package io.pivotal.pivmart.products;

import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerExtension;

import java.io.File;

import static org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode.REMOTE;

public abstract class AbstractFakeNexusSetup {

    @RegisterExtension
    static StubRunnerExtension stubRunnerExtension = new StubRunnerExtension()
            .stubsMode(REMOTE)
            .repoRoot(repoRoot())
            .downloadStub("io.pivotal:product-api").withPort(8081)
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
