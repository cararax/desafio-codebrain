package carara.salesscoresystem.controller;

import carara.salesscoresystem.model.Seller;
import carara.salesscoresystem.service.SellerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
