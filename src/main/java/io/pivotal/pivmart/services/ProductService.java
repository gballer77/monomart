package io.pivotal.pivmart.services;

import io.pivotal.pivmart.models.Catalog;
import io.pivotal.pivmart.repositories.CatalogRepository;
import io.pivotal.pivmart.models.Product;
import io.pivotal.pivmart.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private CatalogRepository catalogRepository;
    private ProductRepository productRepository;

    public ProductService(CatalogRepository catalogRepository, ProductRepository productRepository) {
        this.catalogRepository = catalogRepository;
        this.productRepository = productRepository;
    }

    public List<Product> getForCatalog(String catalogKey) {
        Catalog catalog = catalogRepository.findByKey(catalogKey);
        return productRepository.findAllByCatalog(catalog);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
