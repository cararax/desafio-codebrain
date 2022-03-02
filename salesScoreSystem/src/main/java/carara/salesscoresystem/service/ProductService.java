package carara.salesscoresystem.service;

import carara.salesscoresystem.model.Product;
import carara.salesscoresystem.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ProductService {

    public final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Product insertProduct(Product product) {
        Product newProduct = new Product();
        BeanUtils.copyProperties(product, newProduct);
        return productRepository.save(newProduct);
    }

    @Transactional
    public void deleteProduct(Product product) {
        productRepository.delete(product);

    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }
}
