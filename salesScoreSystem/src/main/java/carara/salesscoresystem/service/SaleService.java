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
import org.springframework.beans.BeanUtils;
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

    public SaleService(SaleRepository saleRepository, ProductRepository productRepository, SellerRepository sellerRepository) {
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
    }

    @Transactional
    public SaleDto insertSale(SaleDto saleDto) {
        if (saleDto.getSellerId() == null) {
            throw new BusinessRuleException("It is not allowed to register a sale without a seller.");
        }

        Optional<Seller> seller = sellerRepository.findById(saleDto.getSellerId());
        if (seller.isEmpty()) {
            throw new EntityNotFoundException("Seller with id  " + saleDto.getSellerId() + " was not found.");
        }
        Sale sale = new Sale();
        sale.setSeller(seller.get());

        if (saleDto.getProductId().isEmpty()) {
            throw new BusinessRuleException("It is not allowed to register a sale without products");
        }
        List<Long> productList = new ArrayList<>(saleDto.getProductId());
//        saleDto.getProducts().clear();
        for (Long productId : productList) {
            Optional<Product> productOptional = productRepository.findById(productId);
            if (productOptional.isEmpty()) {
                throw new EntityNotFoundException("Product with id  " + productId + " was not found.");
            }
            sale.getProducts().add(productOptional.get());
        }

        sale.setLocalDate(LocalDate.now());

        sale.setTotalAmount(sale.calculateTotalAmount(sale.getProducts()));
//        BeanUtils.copyProperties(saleDto, sale);
        BeanUtils.copyProperties(saleRepository.save(sale), saleDto);
        return saleDto;
    }

    public List<SalesAmountBySeller> getSalesAmountBySeller() {
        return saleRepository.countSalesBySeller();
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
