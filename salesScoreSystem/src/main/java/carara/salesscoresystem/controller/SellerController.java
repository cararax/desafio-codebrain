package carara.salesscoresystem.controller;

import carara.salesscoresystem.dto.SellerDto;
import carara.salesscoresystem.model.Product;
import carara.salesscoresystem.model.Seller;
import carara.salesscoresystem.service.SellerService;
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
@RequestMapping("/sellers")
@Log4j2
public class SellerController {

    public final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping("/{sellerId}")
    @Operation(summary = "Get a seller by id.",
            description = "Get a seller a seller by providing the seller id. There must be a seller with the given id.",
            tags = {"Sellers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Seller created.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request.")})
    public ResponseEntity<Object> findById(@PathVariable Long sellerId) {
        log.info("Requested method SellerController.findById({" + sellerId + "})");
        return ResponseEntity.status(HttpStatus.OK).body(sellerService.findById(sellerId));
    }

    @PostMapping
    @Operation(summary = "Create a seller.",
            description = "Create a seller by providing the seller name. Name must not be empty.",
            tags = {"Sellers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Seller created.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SellerDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request.")})
    public ResponseEntity<Seller> insertSeller(@Valid @RequestBody SellerDto sellerDto) {
        log.info("Requested method SellerController.insertSeller()");
        return ResponseEntity.status(HttpStatus.CREATED).body(sellerService.insertSeller(sellerDto));

    }

    @PutMapping("/{sellerId}")
    @Operation(summary = "Update a seller",
            description = "Update a seller by providing a seller id and the seller name. " +
                    "There must be a seller with the given id and name must not be empty.",
            tags = {"Sellers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SellerDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request."),
            @ApiResponse(responseCode = "404", description = "Seller not found.")})
    public ResponseEntity<Object> updateSeller(@PathVariable Long sellerId, @Valid @RequestBody SellerDto sellerDto) {
        log.info("Requested method SellerController.updateSeller(" + sellerId + ")");
        return ResponseEntity.status(HttpStatus.OK).body(sellerService.updateSeller(sellerId, sellerDto));
    }

    @DeleteMapping("/{sellerId}")
    @Operation(summary = "Delete a seller.",
            description = "Delete a seller by providing the seller id, there must be a seller with the given id.",
            tags = {"Sellers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Seller deleted.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SellerDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request."),
            @ApiResponse(responseCode = "404", description = "Seller not found.")})
    public ResponseEntity<String> deleteProduct(@PathVariable Long sellerId) {
        log.info("Requested method SellerController.deleteProduct(" + sellerId + ")");
        return ResponseEntity.status(HttpStatus.OK).body(sellerService.deleteSeller(sellerId));
    }
}
