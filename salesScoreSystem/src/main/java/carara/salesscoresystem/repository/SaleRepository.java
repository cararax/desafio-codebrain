package carara.salesscoresystem.repository;

import carara.salesscoresystem.model.Sale;
import carara.salesscoresystem.projection.SalesAmountBySeller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(value = "select s.id as \"SellerId\", s.name as \"SellerName\", count(s.id) as \"SalesAmount\" from seller s , sale s2 where s.id = s2.seller_id group by s.id", nativeQuery = true)
    List<SalesAmountBySeller> countSalesBySeller();
}
