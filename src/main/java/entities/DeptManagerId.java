package entities;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@ToString
public class DeptManagerId implements Serializable {

    private int employees;
    private String department;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeptManagerId k = (DeptManagerId) o;
        if (!Objects.equals(department, k.department)) return false;
        return employees == k.employees;
    }

    @Override
    public int hashCode() {
        return Objects.hash(department, employees);
    }

    @Override
    public String toString() {
        return "DeptManagerId{" +
                "employees=" + employees +
                "department=" + department +
                '}';
    }
}
