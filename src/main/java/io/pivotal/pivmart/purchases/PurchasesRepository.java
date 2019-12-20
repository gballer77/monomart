package io.pivotal.pivmart.purchases;

import java.util.List;

public interface PurchasesRepository {
    List<Purchase> findAll();

    Purchase save(Purchase purchase);
}
