package carara.salesscoresystem.service;

import carara.salesscoresystem.dto.SaleDto;
import carara.salesscoresystem.dto.TimeIntervalDto;
import carara.salesscoresystem.exception.BusinessRuleException;
import carara.salesscoresystem.exception.EntityNotFoundException;
import carara.salesscoresystem.model.Product;
import carara.salesscoresystem.model.Sale;
import carara.salesscoresystem.model.Seller;
import carara.salesscoresystem.projection.SalesAmountBySeller;
import carara.salesscoresystem.repository.ProductRepository;
import carara.salesscoresystem.repository.SaleRepository;
import carara.salesscoresystem.repository.SellerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {SaleService.class})
@ExtendWith(SpringExtension.class)
class SaleServiceTest {
    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private ProductService productService;

    @MockBean
    private SaleRepository saleRepository;

    @Autowired
    private SaleService saleService;

    @MockBean
    private SellerRepository sellerRepository;

    @MockBean
    private SellerService sellerService;

    @Test
    void testInsertSale() {
        assertThrows(BusinessRuleException.class,
                () -> this.saleService.insertSale(new SaleDto(123L, new ArrayList<>(), LocalDate.ofEpochDay(1L), 10.0)));
        assertThrows(BusinessRuleException.class,
                () -> this.saleService.insertSale(new SaleDto(null, new ArrayList<>(), LocalDate.ofEpochDay(1L), 10.0)));
    }

    @Test
    void testInsertSale2() {
        Seller seller = new Seller();
        seller.setId(123L);
        seller.setName("Name");
        when(this.sellerService.findById((Long) any())).thenReturn(seller);

        Seller seller1 = new Seller();
        seller1.setId(123L);
        seller1.setName("Name");

        Sale sale = new Sale();
        sale.setId(123L);
        sale.setSaleDate(LocalDate.ofEpochDay(1L));
        sale.setProducts(new ArrayList<>());
        sale.setSeller(seller1);
        sale.setTotalAmount(10.0);
        when(this.saleRepository.save((Sale) any())).thenReturn(sale);

        Product product = new Product();
        product.setId(123L);
        product.setName("Name");
        product.setPrice(10.0);
        when(this.productService.findById((Long) any())).thenReturn(product);

        ArrayList<Long> resultLongList = new ArrayList<>();
        resultLongList.add(1L);
        assertSame(sale, this.saleService.insertSale(new SaleDto(123L, resultLongList, LocalDate.ofEpochDay(1L), 10.0)));
        verify(this.sellerService).findById((Long) any());
        verify(this.saleRepository).save((Sale) any());
        verify(this.productService).findById((Long) any());
    }

    @Test
    void testInsertSale3() {
        Seller seller = new Seller();
        seller.setId(123L);
        seller.setName("Name");
        when(this.sellerService.findById((Long) any())).thenReturn(seller);
        when(this.saleRepository.save((Sale) any())).thenThrow(new BusinessRuleException("An error occurred"));

        Product product = new Product();
        product.setId(123L);
        product.setName("Name");
        product.setPrice(10.0);
        when(this.productService.findById((Long) any())).thenReturn(product);

        ArrayList<Long> resultLongList = new ArrayList<>();
        resultLongList.add(1L);
        assertThrows(BusinessRuleException.class,
                () -> this.saleService.insertSale(new SaleDto(123L, resultLongList, LocalDate.ofEpochDay(1L), 10.0)));
        verify(this.sellerService).findById((Long) any());
        verify(this.saleRepository).save((Sale) any());
        verify(this.productService).findById((Long) any());
    }

    @Test
    void testGetSalesAmountBySellers() {
        ArrayList<SalesAmountBySeller> salesAmountBySellerList = new ArrayList<>();
        when(this.saleRepository.countSalesBySellers()).thenReturn(salesAmountBySellerList);
        List<SalesAmountBySeller> actualSalesAmountBySellers = this.saleService.getSalesAmountBySellers();
        assertSame(salesAmountBySellerList, actualSalesAmountBySellers);
        assertTrue(actualSalesAmountBySellers.isEmpty());
        verify(this.saleRepository).countSalesBySellers();
    }

    @Test
    void testGetSalesAmountBySellers2() {
        when(this.saleRepository.countSalesBySellers()).thenThrow(new BusinessRuleException("An error occurred"));
        assertThrows(BusinessRuleException.class, () -> this.saleService.getSalesAmountBySellers());
        verify(this.saleRepository).countSalesBySellers();
    }

    @Test
    void testGetSalesTicketBySeller() {
        Seller seller = new Seller();
        seller.setId(123L);
        seller.setName("Name");
        Optional<Seller> ofResult = Optional.of(seller);
        when(this.sellerRepository.findById((Long) any())).thenReturn(ofResult);
        when(this.saleRepository.salesTicketBySeller((Long) any(), (LocalDate) any(), (LocalDate) any())).thenReturn(null);
        assertNull(this.saleService.getSalesTicketBySeller(123L,
                new TimeIntervalDto(LocalDate.ofEpochDay(1L), LocalDate.ofEpochDay(1L))));
        verify(this.sellerRepository).findById((Long) any());
        verify(this.saleRepository).salesTicketBySeller((Long) any(), (LocalDate) any(), (LocalDate) any());
    }

    @Test
    void testGetSalesTicketBySeller2() {
        Seller seller = new Seller();
        seller.setId(123L);
        seller.setName("Name");
        Optional<Seller> ofResult = Optional.of(seller);
        when(this.sellerRepository.findById((Long) any())).thenReturn(ofResult);
        when(this.saleRepository.salesTicketBySeller((Long) any(), (LocalDate) any(), (LocalDate) any()))
                .thenThrow(new BusinessRuleException("An error occurred"));
        assertThrows(BusinessRuleException.class, () -> this.saleService.getSalesTicketBySeller(123L,
                new TimeIntervalDto(LocalDate.ofEpochDay(1L), LocalDate.ofEpochDay(1L))));
        verify(this.sellerRepository).findById((Long) any());
        verify(this.saleRepository).salesTicketBySeller((Long) any(), (LocalDate) any(), (LocalDate) any());
    }

    @Test
    void testGetSalesTicketBySeller3() {
        when(this.sellerRepository.findById((Long) any())).thenReturn(Optional.empty());
        when(this.saleRepository.salesTicketBySeller((Long) any(), (LocalDate) any(), (LocalDate) any())).thenReturn(null);
        assertThrows(EntityNotFoundException.class, () -> this.saleService.getSalesTicketBySeller(123L,
                new TimeIntervalDto(LocalDate.ofEpochDay(1L), LocalDate.ofEpochDay(1L))));
        verify(this.sellerRepository).findById((Long) any());
    }

    @Test
    void testGetSalesTicketBySeller4() {
        Seller seller = new Seller();
        seller.setId(123L);
        seller.setName("Name");
        Optional<Seller> ofResult = Optional.of(seller);
        when(this.sellerRepository.findById((Long) any())).thenReturn(ofResult);
        when(this.saleRepository.salesTicketBySeller((Long) any(), (LocalDate) any(), (LocalDate) any())).thenReturn(null);
        assertThrows(BusinessRuleException.class, () -> this.saleService.getSalesTicketBySeller(123L,
                new TimeIntervalDto(LocalDate.ofEpochDay(1L), LocalDate.ofEpochDay(0L))));
        verify(this.sellerRepository).findById((Long) any());
    }
}

