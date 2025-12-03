package entities;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Composite IdClass for Many-to-Many relationship between
 * Departments and Employees.
 */@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DeptManagerId implements Serializable {

    /**
     * Primary key of "employees" table.
     */
    private long employees;

    /**
     * Primary key of "departments" table.
     */
    private String department;

    /**
     * Compares the composite keys
     * @param o object to compare DeptManagerId against.
     * @return true if object is equal to DeptMangerId , false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeptManagerId k = (DeptManagerId) o;
        if (!Objects.equals(department, k.department)) return false;
        return employees == k.employees;
    }

    /**
     * Returns a hashcode for the object, calculated based on department and
     * employees parameters.
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(department, employees);
    }

}
