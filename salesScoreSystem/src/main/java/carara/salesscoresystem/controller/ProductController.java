package carara.salesscoresystem.controller;

import carara.salesscoresystem.dto.ProductDto;
import carara.salesscoresystem.model.Product;
import carara.salesscoresystem.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    public final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> insertProduct(@Valid @RequestBody ProductDto product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.insertProduct(product));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @Valid @RequestBody ProductDto productDto) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(productId, productDto));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.deleteProduct(productId));
    }
}
