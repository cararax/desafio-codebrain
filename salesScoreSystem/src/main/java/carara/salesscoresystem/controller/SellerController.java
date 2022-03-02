package carara.salesscoresystem.controller;

import carara.salesscoresystem.model.Seller;
import carara.salesscoresystem.service.SellerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/sellers")
public class SellerController {

    public final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @PostMapping
    public ResponseEntity<Seller> insertSeller(@RequestBody Seller seller) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sellerService.insertSeller(seller));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        Optional<Seller> productToDelete = sellerService.findById(id);
        if (productToDelete.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Seller not found.");
        }
        sellerService.deleteSeller(productToDelete.get());
        return ResponseEntity.status(HttpStatus.OK).body("Seller deleted successfully.");
    }
}
