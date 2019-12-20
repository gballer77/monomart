package io.pivotal.pivmart.products;

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
