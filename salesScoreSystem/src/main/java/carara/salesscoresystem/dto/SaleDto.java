package carara.salesscoresystem.dto;

import carara.salesscoresystem.model.Product;
import carara.salesscoresystem.model.Seller;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SaleDto implements Serializable {
    @NotNull
    private Seller seller;
    @NotEmpty
    private List<Product> products = new ArrayList<>();

    private LocalDate localDate;
    @Positive
    private Double totalAmount;

}
