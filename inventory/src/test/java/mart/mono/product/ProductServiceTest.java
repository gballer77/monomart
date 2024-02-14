package mart.mono.product;

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
