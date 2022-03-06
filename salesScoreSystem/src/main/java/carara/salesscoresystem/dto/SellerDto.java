package carara.salesscoresystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
public class SellerDto {
    @NotEmpty
    private String name;
}
