package carara.salesscoresystem.controller;

import carara.salesscoresystem.dto.SaleDto;
import carara.salesscoresystem.projection.SalesAmountBySeller;
import carara.salesscoresystem.projection.SalesTicket;
import carara.salesscoresystem.service.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {

    public final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    public ResponseEntity<SaleDto> insertSale(@RequestBody SaleDto saleDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(saleService.insertSale(saleDto));
    }

    @GetMapping("/sales-by-seller")
    public ResponseEntity<List<SalesAmountBySeller>> getSalesAmountBySeller() {
        return ResponseEntity.status(HttpStatus.OK).body(saleService.getSalesAmountBySeller());
    }

    @GetMapping("/sales-ticket/{sellerId}")
    public SalesTicket getSalesTicketBySeller(@PathVariable Long sellerId) {
        return saleService.getSalesTicketBySeller(sellerId);
    }

}
