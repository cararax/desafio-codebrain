package carara.salesscoresystem.controller;

import carara.salesscoresystem.dto.ProductDto;
import carara.salesscoresystem.model.Product;
import carara.salesscoresystem.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    public final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> insertProduct(@Valid @RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.insertProduct(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDto productDto) {
        Optional<Product> productToUpdate = productService.findById(id);
        if (productToUpdate.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        Product product = productToUpdate.get();
        BeanUtils.copyProperties(productDto, product);
        product.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(productService.insertProduct(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        Optional<Product> productToDelete = productService.findById(id);
        if (productToDelete.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        productService.deleteProduct(productToDelete.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");
    }
}
