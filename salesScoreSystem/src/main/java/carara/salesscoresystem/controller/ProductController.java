package carara.salesscoresystem.controller;

import carara.salesscoresystem.dto.ProductDto;
import carara.salesscoresystem.model.Product;
import carara.salesscoresystem.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Create a product.",
            description = "Create a product by providing the product name and price. " +
                    "Name must not be empty and price must be greater than zero.",
            tags = {"Products"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product created.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    public ResponseEntity<Product> insertProduct(@Valid @RequestBody ProductDto product) {
        log.info("Requested method ProductController.insertProduct()");
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.insertProduct(product));
    }

    @PutMapping("/{productId}")
    @Operation(summary = "Update a product",
            description = "Update a product by providing a product id and the product name and price. " +
                    "There must be a product with the given id, name must not be empty and price must be " +
                    "greater than zero.",
            tags = {"Products"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request."),
            @ApiResponse(responseCode = "404", description = "Product not found.")})
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @Valid @RequestBody ProductDto productDto) {
        log.info("Requested method ProductController.updateProduct(" + productId + ")");
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(productId, productDto));
    }

    @DeleteMapping("/{productId}")
    @Operation(summary = "Delete a product.",
            description = "Delete a product by providing the product id, there must be a product with the given id.",
            tags = {"Products"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product deleted.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request."),
            @ApiResponse(responseCode = "404", description = "Product not found.")})
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
        log.info("Requested method ProductController.deleteProduct(" + productId + ")");
        return ResponseEntity.status(HttpStatus.OK).body(productService.deleteProduct(productId));
    }
}
