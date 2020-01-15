package io.pivotal.pivmart.repositories;

import io.pivotal.pivmart.models.Purchase;

import java.util.List;

public interface PurchasesRepository {
    List<Purchase> findAll();

    Purchase save(Purchase purchase);
}
