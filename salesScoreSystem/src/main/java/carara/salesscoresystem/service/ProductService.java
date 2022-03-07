package carara.salesscoresystem.service;

import carara.salesscoresystem.dto.ProductDto;
import carara.salesscoresystem.exception.EntityNotFoundException;
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
    public Product insertProduct(ProductDto product) {
        Product productToInsert = new Product();
        BeanUtils.copyProperties(product, productToInsert);
        return productRepository.save(productToInsert);
    }

    @Transactional
    public Product updateProduct(Long productId, ProductDto productDto) {
        Product productToUpdate = findById(productId);
        BeanUtils.copyProperties(productDto, productToUpdate);
        return productRepository.save(productToUpdate);
    }

    @Transactional
    public String deleteProduct(Long productId) {
        Product productToDelete = findById(productId);
        productRepository.delete(productToDelete);
        return "Product with id " + productId + " was deleted successfully.";
    }

    public Product findById(Long productId) {
        Optional<Product> productById = productRepository.findById(productId);
        if (productById.isEmpty()) {
            throw new EntityNotFoundException("Product with id  " + productId + " was not found.");
        }
        return productById.get();
    }
}
