package carara.salesscoresystem.repository;

import carara.salesscoresystem.model.Sale;
import carara.salesscoresystem.projection.SalesAmountBySeller;
import carara.salesscoresystem.projection.SalesTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(value = "select s.id as \"SellerId\", s.name as \"SellerName\", count(s.id) as \"SalesAmount\" from seller s , sale s2 where s.id = s2.seller_id group by s.id order by count(s.id) desc", nativeQuery = true)
    List<SalesAmountBySeller> countSalesBySellers();

    @Query(value = "select se.id as \"SellerId\", se.name as \"SellerName\", AVG(sa.total_amount) as \"SalesTicket\" from seller se, sale sa where se.id = sa.seller_id and se.id = :sellerId and sa.local_date  BETWEEN :startDate AND :endDate \n  group by se.id", nativeQuery = true)
    SalesTicket salesTicketBySeller(Long sellerId, LocalDate startDate, LocalDate endDate);
}
