package io.pivotal.pivmart.catalog;

import java.util.List;

public interface CatalogRepository {
    List<Catalog> findAll();

    Catalog findByKey(String catalogKey);
}
