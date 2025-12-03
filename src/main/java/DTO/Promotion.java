package DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

/**
 * Promotion model
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Promotion {
    /**
     * Variable for employee number
     */
    @JsonProperty(required = true)
    private long empNo;
    /**
     * Variable for new salary
     */
    private BigDecimal newSalary;
    /**
     * Variable for new title
     */
    private String newTitle;
    /**
     * Variable for new department number
     */
    private String newDeptNo;
    /**
     * Variable for isManager
     */
    @JsonProperty("isManager")
    private boolean isManager = false;
}