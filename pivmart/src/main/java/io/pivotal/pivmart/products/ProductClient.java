package io.pivotal.pivmart.products;

import io.pivotal.pivmart.config.ProductApiProperties;
import io.pivotal.pivmart.models.Catalog;
import io.pivotal.pivmart.models.Product;
import io.pivotal.pivmart.repositories.ProductRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Component
public class ProductClient implements ProductRepository {
    private RestTemplate restTemplate;
    private ProductApiProperties productApiProperties;

    public ProductClient(RestTemplate restTemplate, ProductApiProperties productApiProperties) {
        this.restTemplate = restTemplate;
        this.productApiProperties = productApiProperties;
    }

    @Override
    public List<Product> findAll() {
        ResponseEntity<List<Product>> response = restTemplate.exchange(
                URI.create(productApiProperties.getUrl()),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {
                }
        );

        return response.getBody();
    }

    @Override
    public List<Product> findAllByCatalog(Catalog catalog) {
        URI url = URI.create(productApiProperties.getUrl() + "?catalog=" + catalog.getCatalogKey());

        ResponseEntity<List<Product>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {
                }
        );

        return response.getBody();
    }
}
