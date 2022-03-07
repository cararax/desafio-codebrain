package carara.salesscoresystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SaleDto implements Serializable {
    @NotNull
    private Long sellerId;
    @NotEmpty
    private List<Long> productId;

    private LocalDate localDate;
    @Positive
    private Double totalAmount;

}
