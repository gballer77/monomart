package mart.mono.services;

import mart.mono.product.repositories.ProductRepository;
import mart.mono.product.services.ProductService;
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
