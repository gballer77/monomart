package io.pivotal.pivmart.database;

import io.pivotal.pivmart.purchases.Purchase;
import io.pivotal.pivmart.purchases.PurchasesRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PurchasesStaticDataRepository implements PurchasesRepository {

    private StaticDatabase staticDatabase;

    public PurchasesStaticDataRepository(StaticDatabase staticDatabase) {
        this.staticDatabase = staticDatabase;
    }

    @Override
    public List<Purchase> findAll() {
        return new ArrayList<>(staticDatabase.getPurchases().values());
    }

    @Override
    public Purchase save(Purchase purchase) {
        return staticDatabase.save(purchase);
    }
}
