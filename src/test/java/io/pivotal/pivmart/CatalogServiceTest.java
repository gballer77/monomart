package io.pivotal.pivmart;

import io.pivotal.pivmart.catalog.Catalog;
import io.pivotal.pivmart.catalog.CatalogRepository;
import io.pivotal.pivmart.catalog.CatalogService;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CatalogServiceTest {

    @Mock
    CatalogRepository catalogRepository;

    @InjectMocks
    CatalogService catalogService;

    @Test
    void getAll() {
        List<Catalog> expectedCatalog = asList(Catalog.builder()
                .name(RandomString.make())
                .build());

        when(catalogRepository.findAll())
                .thenReturn(expectedCatalog);

        List<Catalog> catalogs = catalogService.getAll();

        assertThat(catalogs).isEqualTo(expectedCatalog);
    }
}
