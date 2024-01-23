package mart.mono.repositories;

import mart.mono.models.Product;
import mart.mono.models.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PurchasesRepository extends JpaRepository<Purchase, UUID> {

}
