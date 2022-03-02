package carara.salesscoresystem.service;

import carara.salesscoresystem.dto.ProductDto;
import carara.salesscoresystem.model.Product;
import carara.salesscoresystem.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProductService {

    public final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Object insertProduct(ProductDto productDto) {
        Product newProduct = new Product();
        BeanUtils.copyProperties(productDto, newProduct);
        return productRepository.save(newProduct);
    }
}
