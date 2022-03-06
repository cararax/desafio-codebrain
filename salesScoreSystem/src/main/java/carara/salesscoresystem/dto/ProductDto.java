package carara.salesscoresystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Getter
@Setter
@AllArgsConstructor
public class ProductDto {
    @NotEmpty
    private String name;
    @Positive
    private Double price;
}
