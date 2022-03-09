package carara.salesscoresystem;

import carara.salesscoresystem.model.Product;
import carara.salesscoresystem.model.Sale;
import carara.salesscoresystem.model.Seller;
import carara.salesscoresystem.repository.ProductRepository;
import carara.salesscoresystem.repository.SaleRepository;
import carara.salesscoresystem.repository.SellerRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
@Log4j2
public class DataLoader implements CommandLineRunner {


    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;
    private final SaleRepository saleRepository;


    public DataLoader(ProductRepository productRepository, SellerRepository sellerRepository, SaleRepository saleRepository) {
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
        this.saleRepository = saleRepository;
    }


    @Override
    public void run(String... args) {
        if (!productRepository.existsById(1L)) {
            Product product0 = new Product(null, "Apple", 0.11);
            Product product1 = new Product(null, "Banana", 1.11);
            Product product2 = new Product(null, "Coconut", 2.22);
            Product product3 = new Product(null, "Pineaple", 3.33);
            Product product4 = new Product(null, "Mango", 4.44);
            Product product5 = new Product(null, "Melon", 5.55);
            Product product6 = new Product(null, "Strawberry", 6.66);
            Product product7 = new Product(null, "Kiwi", 7.77);
            Product product8 = new Product(null, "Avocado", 8.88);
            Product product9 = new Product(null, "Lime", 9.99);
            List<Product> productList = Arrays.asList(product0, product1, product2, product3, product4, product5, product6, product7, product8, product9);
            productRepository.saveAll(productList);
        }

        if (!sellerRepository.existsById(11L)) {
            Seller seller0 = new Seller(null, "Kendrick Figueroa");
            Seller seller1 = new Seller(null, "Anika Jeffery");
            Seller seller2 = new Seller(null, "Anna Philip");
            Seller seller3 = new Seller(null, "Nichole Thomas");
            Seller seller4 = new Seller(null, "John Doe");

            List<Seller> sellerList = Arrays.asList(seller0, seller1, seller2, seller3, seller4);
            sellerRepository.saveAll(sellerList);
        }

        if (!saleRepository.existsById(16L)) {
            List<Product> productList = productRepository.findAll();

            Sale sale0 = new Sale(null, sellerRepository.findById(11L).get(), productList, LocalDate.parse("2022-02-01"), Sale.calculateTotalAmount(productList));
            removeFirstElement(productList);
            Sale sale1 = new Sale(null, sellerRepository.findById(11L).get(), productList, LocalDate.parse("2022-02-03"), Sale.calculateTotalAmount(productList));
            removeFirstElement(productList);
            Sale sale2 = new Sale(null, sellerRepository.findById(12L).get(), productList, LocalDate.parse("2022-02-07"), Sale.calculateTotalAmount(productList));
            removeFirstElement(productList);
            Sale sale3 = new Sale(null, sellerRepository.findById(12L).get(), productList, LocalDate.parse("2022-02-11"), Sale.calculateTotalAmount(productList));
            removeFirstElement(productList);
            Sale sale4 = new Sale(null, sellerRepository.findById(13L).get(), productList, LocalDate.parse("2022-02-15"), Sale.calculateTotalAmount(productList));
            removeFirstElement(productList);
            Sale sale5 = new Sale(null, sellerRepository.findById(13L).get(), productList, LocalDate.parse("2022-02-18"), Sale.calculateTotalAmount(productList));
            removeFirstElement(productList);
            Sale sale6 = new Sale(null, sellerRepository.findById(14L).get(), productList, LocalDate.parse("2022-02-22"), Sale.calculateTotalAmount(productList));
            removeFirstElement(productList);
            Sale sale7 = new Sale(null, sellerRepository.findById(15L).get(), productList, LocalDate.parse("2022-02-25"), Sale.calculateTotalAmount(productList));
            removeFirstElement(productList);
            Sale sale8 = new Sale(null, sellerRepository.findById(15L).get(), productList, LocalDate.parse("2022-02-26"), Sale.calculateTotalAmount(productList));
            removeFirstElement(productList);
            Sale sale9 = new Sale(null, sellerRepository.findById(15L).get(), productList, LocalDate.parse("2022-02-28"), Sale.calculateTotalAmount(productList));

            List<Sale> saleList = Arrays.asList(sale0, sale1, sale2, sale3, sale4, sale5, sale6, sale7, sale8, sale9);
            saleRepository.saveAll(saleList);
        }

        log.info("Database successfully populated");
    }

    public void removeFirstElement(List<Product> productList) {
        productList.remove(0);
    }
}
