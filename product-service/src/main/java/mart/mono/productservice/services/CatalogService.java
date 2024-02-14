package mart.mono.productservice.services;

import mart.mono.productservice.models.Catalog;
import mart.mono.productservice.repositories.CatalogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {

    CatalogRepository catalogRepository;

    public CatalogService(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    public List<Catalog> getAll() {
        return catalogRepository.findAll();
    }
}
