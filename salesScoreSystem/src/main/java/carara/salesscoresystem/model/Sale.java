package carara.salesscoresystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "seller_id", nullable = false)
    @NotNull
    private Seller seller;

    @ManyToMany
    @JoinTable(name = "sale_products",
            joinColumns = @JoinColumn(name = "sale_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    @NotEmpty
    private List<Product> products = new ArrayList<>();
    @NotNull
    private LocalDate saleDate;
    @Positive
    private Double totalAmount = null;

    public static Double calculateTotalAmount(List<Product> products) {
        Double total = 0.0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }
}
