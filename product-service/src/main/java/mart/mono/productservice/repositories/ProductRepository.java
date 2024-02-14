package mart.mono.productservice.repositories;

import mart.mono.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    public List<Product> findByCatalogId(String catalogId);
}
