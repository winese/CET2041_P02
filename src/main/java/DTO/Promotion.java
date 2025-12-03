package DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Promotion {
    @JsonProperty(required = true)
    private long empNo;
    private BigDecimal newSalary;
    private String newTitle;
    private String newDeptNo;
    @JsonProperty("isManager")
    private boolean isManager = false;
}