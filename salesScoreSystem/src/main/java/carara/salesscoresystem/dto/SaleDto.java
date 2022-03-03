package carara.salesscoresystem.dto;

import carara.salesscoresystem.model.Product;
import carara.salesscoresystem.model.Seller;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SaleDto implements Serializable {

    private Seller seller;
    private List<Product> products = new ArrayList<>();
    private Double totalAmount;

}
