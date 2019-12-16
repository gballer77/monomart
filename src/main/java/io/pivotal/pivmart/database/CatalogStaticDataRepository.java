package io.pivotal.pivmart.database;

import io.pivotal.pivmart.catalog.Catalog;
import io.pivotal.pivmart.catalog.CatalogRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CatalogStaticDataRepository implements CatalogRepository {
    StaticDatabase staticDatabase;

    public CatalogStaticDataRepository(StaticDatabase staticDatabase) {
        this.staticDatabase = staticDatabase;
    }

    @Override
    public List<Catalog> findAll() {
        return new ArrayList<>(staticDatabase.getCatalogs().values());
    }

    @Override
    public Catalog findByKey(String catalogKey) {
        return staticDatabase.getCatalogs().values().stream()
                .filter(c -> catalogKey.equals(c.getKey()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Could not find catalog with key %s", catalogKey)));
    }
}
