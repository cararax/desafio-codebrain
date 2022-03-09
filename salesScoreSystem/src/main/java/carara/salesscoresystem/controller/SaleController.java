package carara.salesscoresystem.controller;

import carara.salesscoresystem.dto.SaleDto;
import carara.salesscoresystem.dto.TimeIntervalDto;
import carara.salesscoresystem.model.Sale;
import carara.salesscoresystem.projection.SalesAmountBySeller;
import carara.salesscoresystem.projection.SalesTicket;
import carara.salesscoresystem.service.SaleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sales")
@Log4j2
public class SaleController {

    public final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    public ResponseEntity<Sale> insertSale(@Valid @RequestBody SaleDto saleDto) {
        log.info("Requested method SaleController.insertSale()");
        return ResponseEntity.status(HttpStatus.CREATED).body(saleService.insertSale(saleDto));
    }

    @GetMapping("/sales-by-seller")
    public ResponseEntity<List<SalesAmountBySeller>> getSalesAmountBySellers() {
        log.info("Requested method SaleController.getSalesAmountBySellers()");
        return ResponseEntity.status(HttpStatus.OK).body(saleService.getSalesAmountBySellers());
    }

    @GetMapping("/sales-ticket/{sellerId}")
    public ResponseEntity<SalesTicket> getSalesTicketBySeller(@PathVariable Long sellerId, @RequestBody TimeIntervalDto timeIntervalDto) {
        log.info("Requested method SaleController.getSalesTicketBySeller(sellerId:" + sellerId + ", startDate: " + timeIntervalDto.getStartDate() + ", endDate: " + timeIntervalDto.getEndDate() + ")");
        return ResponseEntity.status(HttpStatus.OK).body(saleService.getSalesTicketBySeller(sellerId, timeIntervalDto));
    }

}
