package carara.salesscoresystem.controller;

import carara.salesscoresystem.dto.SellerDto;
import carara.salesscoresystem.model.Seller;
import carara.salesscoresystem.service.SellerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/sellers")
public class SellerController {

    public final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping("/{sellerId}")
    public ResponseEntity<Object> findById(@PathVariable Long sellerId) {
        return ResponseEntity.status(HttpStatus.OK).body(sellerService.findById(sellerId));
    }

    @PostMapping
    public ResponseEntity<Seller> insertSeller(@Valid @RequestBody SellerDto sellerDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sellerService.insertSeller(sellerDto));
    }

    @PutMapping("/{sellerId}")
    public ResponseEntity<Object> updateSeller(@PathVariable Long sellerId, @Valid @RequestBody SellerDto sellerDto) {
        return ResponseEntity.status(HttpStatus.OK).body(sellerService.updateSeller(sellerId, sellerDto));
    }

    @DeleteMapping("/{sellerId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long sellerId) {
        return ResponseEntity.status(HttpStatus.OK).body(sellerService.deleteSeller(sellerId));
    }
}
