package io.pivotal.pivmart.services;

import io.pivotal.pivmart.repositories.CatalogRepository;
import io.pivotal.pivmart.repositories.ProductRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ProductServiceTest {

    @Test
    void getAll() {
        ProductRepository productRepository = mock(ProductRepository.class);

        ProductService productService = new ProductService(productRepository);

        productService.getAll();

        verify(productRepository).findAll();
    }
}
