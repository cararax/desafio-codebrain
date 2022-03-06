package carara.salesscoresystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class TimeIntervalDto {
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;

}
