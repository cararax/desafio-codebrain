package carara.salesscoresystem.service;

import carara.salesscoresystem.dto.SaleDto;
import carara.salesscoresystem.dto.TimeIntervalDto;
import carara.salesscoresystem.exception.BusinessRuleException;
import carara.salesscoresystem.exception.EntityNotFoundException;
import carara.salesscoresystem.model.Product;
import carara.salesscoresystem.model.Sale;
import carara.salesscoresystem.model.Seller;
import carara.salesscoresystem.projection.SalesAmountBySeller;
import carara.salesscoresystem.projection.SalesTicket;
import carara.salesscoresystem.repository.ProductRepository;
import carara.salesscoresystem.repository.SaleRepository;
import carara.salesscoresystem.repository.SellerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    public final SaleRepository saleRepository;
    public final ProductRepository productRepository;
    public final SellerRepository sellerRepository;

    public final SellerService sellerService;
    public final ProductService productService;

    public SaleService(SaleRepository saleRepository, ProductRepository productRepository, SellerRepository sellerRepository, SellerService sellerService, ProductService productService) {
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
        this.sellerService = sellerService;
        this.productService = productService;
    }

    @Transactional
    public Sale insertSale(SaleDto saleDto) {
        if (saleDto.getSellerId() == null) {
            throw new BusinessRuleException("It is not allowed to register a sale without a seller.");
        }
        if (saleDto.getProductId().isEmpty()) {
            throw new BusinessRuleException("It is not allowed to register a sale without products");
        }
        Sale sale = new Sale();
        Seller seller = sellerService.findById(saleDto.getSellerId());
        sale.setSeller(seller);
        populateProducts(saleDto, sale);
        sale.setLocalDate(LocalDate.now());
        sale.setTotalAmount(sale.calculateTotalAmount(sale.getProducts()));
        return saleRepository.save(sale);
    }

    private void populateProducts(SaleDto saleDto, Sale sale) {
        List<Long> productList = new ArrayList<>(saleDto.getProductId());
        for (Long productId : productList) {
            Product product = productService.findById(productId);
            sale.getProducts().add(product);
        }
    }

    public List<SalesAmountBySeller> getSalesAmountBySellers() {
        return saleRepository.countSalesBySellers();
    }

    public SalesTicket getSalesTicketBySeller(Long sellerId, TimeIntervalDto timeIntervalDto) {
        Optional<Seller> seller = sellerRepository.findById(sellerId);
        if (seller.isEmpty()) {
            throw new EntityNotFoundException("Seller with id  " + sellerId + " was not found.");
        }
        if (timeIntervalDto.getEndDate().isBefore(timeIntervalDto.getStartDate())) {
            throw new BusinessRuleException("The start date must be before the end date");
        }
        LocalDate startDate = timeIntervalDto.getStartDate();
        LocalDate endDate = timeIntervalDto.getEndDate();
        return saleRepository.salesTicketBySeller(sellerId, startDate, endDate);
    }
}
