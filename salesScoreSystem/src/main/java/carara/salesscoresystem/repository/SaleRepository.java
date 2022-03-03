package carara.salesscoresystem.repository;

import carara.salesscoresystem.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
