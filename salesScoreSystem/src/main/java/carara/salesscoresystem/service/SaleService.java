package carara.salesscoresystem.service;

import carara.salesscoresystem.dto.SaleDto;
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

        Optional<Seller> seller = sellerRepository.findById(saleDto.getSeller().getId());
        if (seller.isPresent()) {
            saleDto.setSeller(seller.get());
        }
        List<Product> productList = new ArrayList<>(saleDto.getProducts());
        saleDto.getProducts().clear();
        for (Product product : productList) {
            Optional<Product> productOptional = productRepository.findById(product.getId());
            if (productOptional.isPresent()) {
                saleDto.getProducts().add(productOptional.get());
            }
        }

        Sale sale = new Sale();
        saleDto.setTotalAmount(sale.calculateTotalAmount(saleDto.getProducts()));
        BeanUtils.copyProperties(saleDto, sale);
        BeanUtils.copyProperties(saleRepository.save(sale), saleDto);
        return saleDto;
    }

    public List<SalesAmountBySeller> getSalesAmountBySeller() {
        return saleRepository.countSalesBySeller();
    }

    public SalesTicket getSalesTicketBySeller(Long sellerId) {
        return saleRepository.salesTicketBySeller(sellerId);
    }
}
