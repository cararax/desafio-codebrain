package carara.salesscoresystem.controller;

import carara.salesscoresystem.dto.ProductDto;
import carara.salesscoresystem.model.Seller;
import carara.salesscoresystem.service.SellerService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/sellers")
public class SellerController {

    public final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Optional<Seller> seller = sellerService.findById(id);
        if (seller.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Seller not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(seller.get());
    }

    @PostMapping
    public ResponseEntity<Seller> insertSeller(@Valid @RequestBody Seller seller) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sellerService.insertSeller(seller));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSeller(@PathVariable Long id, @Valid @RequestBody ProductDto productDto) {
        Optional<Seller> productToUpdate = sellerService.findById(id);
        if (productToUpdate.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Seller not found.");
        }
        Seller seller = productToUpdate.get();
        BeanUtils.copyProperties(productDto, seller);
        seller.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(sellerService.insertSeller(seller));
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
