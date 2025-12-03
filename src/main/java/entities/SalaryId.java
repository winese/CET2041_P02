package entities;

import jakarta.persistence.Embeddable;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

//@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SalaryId implements Serializable {
    private long employees;
    private LocalDate fromDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalaryId s = (SalaryId) o;
        if (!Objects.equals(fromDate, s.fromDate)) return false;
        return employees == s.employees;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employees, fromDate);
    }

//    @Override
//    public String toString() {
//        return "SalaryId{" +
//                "employees=" + employees +
//                "fromDate=" + fromDate +
//                '}';
//    }
}