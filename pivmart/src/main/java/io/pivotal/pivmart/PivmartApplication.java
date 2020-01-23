package io.pivotal.pivmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PivmartApplication {

    public static void main(String[] args) {
        SpringApplication.run(PivmartApplication.class, args);
    }

	@Profile("cloud")
	@Configuration
	public static class Cloud {

		@Bean
		@LoadBalanced
		@Primary
		public RestTemplate loadBalancedRestTemplate(RestTemplateBuilder builder) {

			return builder.build();
		}

	}

}
