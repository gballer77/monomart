package io.pivotal.pivmart.products;

import java.util.List;

public interface CatalogRepository {
    List<Catalog> findAll();

    Catalog findByKey(String catalogKey);
}
