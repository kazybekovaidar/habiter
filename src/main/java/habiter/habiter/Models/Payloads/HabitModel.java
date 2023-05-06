package habiter.habiter.Models.Payloads;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HabitModel {
    private String description;
    private String name;
    private String unit;
    private Integer goal;
    private LocalDate startDate;
    private LocalDate endDate;
}