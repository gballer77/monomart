package io.pivotal.pivmart;

import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;


    @Test
    void getForCatalog() {
        String expectedCatalogName = RandomString.make();
        Product product = Product.builder()
                .catalog(expectedCatalogName)
                .build();

        when(productRepository.findAllByCatalog(expectedCatalogName))
                .thenReturn(asList(product));

        List<Product> products = productService.getForCatalog(expectedCatalogName);
        assertThat(products).hasSize(1);
        assertThat(products.get(0).getCatalog()).isEqualTo(expectedCatalogName);

        verify(productRepository).findAllByCatalog(expectedCatalogName);
    }
}
