package carara.salesscoresystem.controller;

import carara.salesscoresystem.dto.ProductDto;
import carara.salesscoresystem.model.Product;
import carara.salesscoresystem.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
@Log4j2
public class ProductController {

    public final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> insertProduct(@Valid @RequestBody ProductDto product) {
        log.info("Requested method ProductController.insertProduct()");
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.insertProduct(product));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @Valid @RequestBody ProductDto productDto) {
        log.info("Requested method ProductController.updateProduct(" + productId + ")");
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(productId, productDto));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
        log.info("Requested method ProductController.deleteProduct(" + productId + ")");
        return ResponseEntity.status(HttpStatus.OK).body(productService.deleteProduct(productId));
    }
}
