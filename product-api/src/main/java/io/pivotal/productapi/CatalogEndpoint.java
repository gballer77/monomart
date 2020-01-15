package io.pivotal.productapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CatalogEndpoint {
    private CatalogRepository catalogRepository;

    public CatalogEndpoint(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @GetMapping("/catalogs")
    public List<Catalog> findAll() {
        return catalogRepository.findAll();
    }

    @GetMapping("/catalogs/{key}")
    public Catalog findByKey(@PathVariable("key") String catalogKey) {
        return catalogRepository.findByKey(catalogKey);
    }
}
