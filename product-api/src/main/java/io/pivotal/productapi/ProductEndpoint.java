package io.pivotal.productapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductEndpoint {
    private ProductRepository productRepository;
    private CatalogRepository catalogRepository;

    public ProductEndpoint(ProductRepository productRepository, CatalogRepository catalogRepository) {
        this.productRepository = productRepository;
        this.catalogRepository = catalogRepository;
    }

    @GetMapping("/")
    public List<Product> findAll(@RequestParam(value = "catalog", required = false) String catalogKey) {
        if (catalogKey == null) {
            return productRepository.findAll();
        }

        Catalog catalog = catalogRepository.findByKey(catalogKey);
        return productRepository.findAllByCatalog(catalog);
    }
}
