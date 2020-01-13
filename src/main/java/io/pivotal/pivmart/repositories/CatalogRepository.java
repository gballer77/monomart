package io.pivotal.pivmart.repositories;

import io.pivotal.pivmart.models.Catalog;

import java.util.List;

public interface CatalogRepository {
    List<Catalog> findAll();

    Catalog findByKey(String catalogKey);
}
