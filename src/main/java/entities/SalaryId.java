package entities;

import lombok.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * SalariesId entity class
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SalaryId implements Serializable {
    /**
     * Variable for employee number
     */
    private long employees;
    /**
     * Variable for fromDate
     */
    private LocalDate fromDate;

    /**
     * Object logical equality method
     * @param o object
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalaryId s = (SalaryId) o;
        if (!Objects.equals(fromDate, s.fromDate)) return false;
        return employees == s.employees;
    }

    /**
     * Object hash code method
     * @return object hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(employees, fromDate);
    }
}