package io.pivotal.pivmart.product;

import io.pivotal.pivmart.catalog.Catalog;

import java.util.List;

public interface ProductRepository {
    List<Product> findAllByCatalog(Catalog catalog);

    List<Product> findAll();
}
