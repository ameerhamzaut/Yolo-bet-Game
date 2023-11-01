package yolo.bet.project.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @NotNull
    private Double bet;
    @NotNull
    @Min(1)
    @Max(100)
    private int wholenumber;

}
