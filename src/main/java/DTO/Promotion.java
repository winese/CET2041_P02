package DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Promotion {
    @JsonProperty(required = true)
    private int empNo;
    private int newSalary;
    private String newTitle;
    private String newDeptNo;
    @JsonProperty("isManager")
    private boolean isManager = false;
}