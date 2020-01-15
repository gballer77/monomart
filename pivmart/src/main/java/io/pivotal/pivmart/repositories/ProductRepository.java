package io.pivotal.pivmart.repositories;

import io.pivotal.pivmart.models.Catalog;
import io.pivotal.pivmart.models.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAllByCatalog(Catalog catalog);

    List<Product> findAll();
}
