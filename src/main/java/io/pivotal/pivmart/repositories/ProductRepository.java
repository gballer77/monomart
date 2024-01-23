package io.pivotal.pivmart.repositories;

import io.pivotal.pivmart.models.CartItem;
import io.pivotal.pivmart.models.Catalog;
import io.pivotal.pivmart.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    public List<Product> findByCatalogId(String catalogId);
}
