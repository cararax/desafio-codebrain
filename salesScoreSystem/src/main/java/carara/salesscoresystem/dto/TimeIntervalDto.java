package carara.salesscoresystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class TimeIntervalDto {
    private LocalDate startDate;
    private LocalDate endDate;

}
