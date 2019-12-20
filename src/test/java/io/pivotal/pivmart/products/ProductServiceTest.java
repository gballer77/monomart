package io.pivotal.pivmart.products;

import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    CatalogRepository catalogRepository;

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;


    @Test
    void getForCatalog() {
        String expectedCatalogName = RandomString.make();

        UUID expectedCatalogId = UUID.randomUUID();
        Catalog expectedCatalog = Catalog.builder().id(expectedCatalogId).build();
        when(catalogRepository.findByKey(expectedCatalogName))
                .thenReturn(expectedCatalog);

        Product product = Product.builder()
                .catalogId(expectedCatalog.getId())
                .build();

        when(productRepository.findAllByCatalog(expectedCatalog))
                .thenReturn(asList(product));

        List<Product> products = productService.getForCatalog(expectedCatalogName);
        assertThat(products).hasSize(1);
        assertThat(products.get(0).getCatalogId()).isEqualTo(expectedCatalogId);

        verify(productRepository).findAllByCatalog(expectedCatalog);
    }
}
