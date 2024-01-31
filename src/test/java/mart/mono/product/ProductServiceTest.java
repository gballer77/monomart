package mart.mono.product;

import mart.mono.product.ProductService;
import mart.mono.product.ProductRepository;
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
