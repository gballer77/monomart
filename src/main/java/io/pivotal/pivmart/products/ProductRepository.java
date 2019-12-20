package io.pivotal.pivmart.products;

import java.util.List;

public interface ProductRepository {
    List<Product> findAllByCatalog(Catalog catalog);

    List<Product> findAll();
}
