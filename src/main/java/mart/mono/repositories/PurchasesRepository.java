package mart.mono.repositories;

import mart.mono.models.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PurchasesRepository extends JpaRepository<Purchase, UUID> {

}
