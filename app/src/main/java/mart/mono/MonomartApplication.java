package mart.mono;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = "mart.product")
@ComponentScan("mart.product")
@EnableJpaRepositories(basePackages = "mart.product")
@EntityScan("mart.product.*")
public class MonomartApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonomartApplication.class, args);
    }

	@Profile("cloud")
	@Configuration
	public static class Cloud {

		@Bean
		@Primary
		public RestTemplate loadBalancedRestTemplate(RestTemplateBuilder builder) {

			return builder.build();
		}

	}

}
