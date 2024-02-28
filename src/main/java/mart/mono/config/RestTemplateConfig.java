package mart.mono.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder, ProductApiProperties productApiProperties) {
        return restTemplateBuilder
                .rootUri(productApiProperties.getUrl())
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean
    public RestClient restClient(ProductApiProperties productApiProperties) {
        return RestClient.builder()
                .baseUrl(productApiProperties.getUrl())
                .build();
    }
}
