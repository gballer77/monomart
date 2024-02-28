package mart.mono;

import mart.mono.product.ProductRepository;
import mart.mono.product.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ProductServiceTest {

    @Test
    void getAll() {
        ProductRepository productRepository = Mockito.mock(ProductRepository.class);

        ProductService productService = new ProductService(productRepository);

        productService.getAll();

        Mockito.verify(productRepository).findAll();
    }
}
