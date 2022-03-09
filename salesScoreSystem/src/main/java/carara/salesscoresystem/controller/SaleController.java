package carara.salesscoresystem.controller;

import carara.salesscoresystem.dto.SaleDto;
import carara.salesscoresystem.dto.TimeIntervalDto;
import carara.salesscoresystem.model.Sale;
import carara.salesscoresystem.projection.SalesAmountBySeller;
import carara.salesscoresystem.projection.SalesTicket;
import carara.salesscoresystem.service.SaleService;
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
    @Operation(summary = "Create a sale.",
            description = "Create a sale by providing the seller id and a list of product ids. Name must not be empty. " +
                    "There must be a seller with the given id and there must be one product for each given product id",
            tags = {"Sales"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sale created.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SaleDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request.")})
    public ResponseEntity<Sale> insertSale(@Valid @RequestBody SaleDto saleDto) {
        log.info("Requested method SaleController.insertSale()");
        return ResponseEntity.status(HttpStatus.CREATED).body(saleService.insertSale(saleDto));
    }

    @GetMapping("/sales-by-seller")
    @Operation(summary = "Get the sellers with the highest number of sales.",
            description = "Returns the list of sellers with their respective sales amount ordered by the highest number of sales.",
            tags = {"Sellers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SalesAmountBySeller.class))})})
    public ResponseEntity<List<SalesAmountBySeller>> getSalesAmountBySellers() {
        log.info("Requested method SaleController.getSalesAmountBySellers()");
        return ResponseEntity.status(HttpStatus.OK).body(saleService.getSalesAmountBySellers());
    }

    @GetMapping("/sales-ticket/{sellerId}")
    @Operation(summary = "Get the average sales ticket of a seller by time interval.",
            description = "Returns the value of the sales ticket and the seller name. Must inform the id of an existing " +
                    "seller and a date range",
            tags = {"Sellers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SalesTicket.class))})})
    public ResponseEntity<SalesTicket> getSalesTicketBySeller(@PathVariable Long sellerId,
                                                              @RequestBody TimeIntervalDto timeIntervalDto) {
        log.info("Requested method SaleController.getSalesTicketBySeller(sellerId:" + sellerId +
                ", startDate: " + timeIntervalDto.getStartDate() + ", endDate: " + timeIntervalDto.getEndDate() + ")");
        return ResponseEntity.status(HttpStatus.OK).body(saleService.getSalesTicketBySeller(sellerId, timeIntervalDto));
    }

}
