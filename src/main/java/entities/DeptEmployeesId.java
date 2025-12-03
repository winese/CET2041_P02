package entities;

import lombok.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Composite IdClass for Many-to-Many relationship between
 * Departments and Employees.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DeptEmployeesId implements Serializable {

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
     * @param o object to compare DeptEmployeesId against.
     * @return true if object is equal to DeptEmployeeId , false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeptEmployeesId k = (DeptEmployeesId) o;
        return employees == k.employees && Objects.equals(department, k.department);
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
