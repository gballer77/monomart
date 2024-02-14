package mart.purchase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PurchasesRepository extends JpaRepository<Purchase, UUID> {

}
