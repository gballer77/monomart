package io.pivotal.pivmart.products;

import io.pivotal.pivmart.models.Catalog;
import io.pivotal.pivmart.models.Product;
import io.pivotal.pivmart.repositories.CatalogRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Component
public class CatalogClient implements CatalogRepository {
    private RestTemplate restTemplate;

    public CatalogClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Catalog> findAll() {
        ResponseEntity<List<Catalog>> response = restTemplate.exchange(
                URI.create("http://localhost:8081/catalogs/"),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Catalog>>() {}
        );

        return response.getBody();
    }

    @Override
    public Catalog findByKey(String catalogKey) {
        ResponseEntity<Catalog> response = restTemplate.exchange(
                URI.create("http://localhost:8081/catalogs/" + catalogKey),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Catalog>() {}
        );

        return response.getBody();
    }
}
