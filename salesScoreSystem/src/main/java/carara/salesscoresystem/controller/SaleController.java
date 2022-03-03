package carara.salesscoresystem.controller;

import carara.salesscoresystem.dto.SaleDto;
import carara.salesscoresystem.service.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
