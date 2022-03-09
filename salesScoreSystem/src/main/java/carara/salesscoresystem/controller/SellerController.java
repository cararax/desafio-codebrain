package carara.salesscoresystem.controller;

import carara.salesscoresystem.dto.SellerDto;
import carara.salesscoresystem.model.Seller;
import carara.salesscoresystem.service.SellerService;
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
    public ResponseEntity<Object> findById(@PathVariable Long sellerId) {
        log.info("Requested method SellerController.findById({" + sellerId + "})");
        return ResponseEntity.status(HttpStatus.OK).body(sellerService.findById(sellerId));
    }

    @PostMapping
    public ResponseEntity<Seller> insertSeller(@Valid @RequestBody SellerDto sellerDto) {
        log.info("Requested method SellerController.insertSeller()");
        return ResponseEntity.status(HttpStatus.CREATED).body(sellerService.insertSeller(sellerDto));

    }

    @PutMapping("/{sellerId}")
    public ResponseEntity<Object> updateSeller(@PathVariable Long sellerId, @Valid @RequestBody SellerDto sellerDto) {
        log.info("Requested method SellerController.updateSeller(" + sellerId + ")");
        return ResponseEntity.status(HttpStatus.OK).body(sellerService.updateSeller(sellerId, sellerDto));
    }

    @DeleteMapping("/{sellerId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long sellerId) {
        log.info("Requested method SellerController.deleteProduct(" + sellerId + ")");
        return ResponseEntity.status(HttpStatus.OK).body(sellerService.deleteSeller(sellerId));
    }
}
