package io.pivotal.pivmart.database;

import io.pivotal.pivmart.products.Catalog;

import java.util.UUID;

public class CatalogFactory {
    public static Catalog create(String name) {
        return Catalog.builder()
                .id(UUID.randomUUID())
                .displayName(name)
                .catalogKey(name.toLowerCase().replaceAll("\\s+",""))
                .build();
    }
}
