package mart.mono.repositories;

import mart.mono.models.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PurchasesRepository extends JpaRepository<Purchase, UUID> {

}
