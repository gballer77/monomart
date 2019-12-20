package io.pivotal.pivmart.database;

import io.pivotal.pivmart.products.Catalog;
import io.pivotal.pivmart.products.Product;
import io.pivotal.pivmart.products.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductStaticDataRepository implements ProductRepository {

    private StaticDatabase staticDatabase;

    public ProductStaticDataRepository(StaticDatabase staticDatabase) {
        this.staticDatabase = staticDatabase;
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(staticDatabase.getProducts().values());
    }

    @Override
    public List<Product> findAllByCatalog(Catalog catalog) {
        return staticDatabase.getProducts().values().stream()
                .filter(p -> catalog.getId().equals(p.getCatalogId()))
                .collect(Collectors.toList());

    }
}
