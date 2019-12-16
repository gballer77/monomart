package io.pivotal.pivmart;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getForCatalog(String catalog) {
        return productRepository.findAllByCatalog(catalog);
    }
}
