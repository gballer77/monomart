package catalog.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.catalog.Catalog;
import java.util.List;

@RestController
@RequestMapping("api/catalogs")
public class CatalogRestController {

    private CatalogService catalogService;

    public CatalogRestController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping
    public List<Catalog> list() {
        return catalogService.getAll();
    }

}
