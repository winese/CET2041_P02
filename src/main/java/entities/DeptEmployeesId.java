package entities;

import lombok.*;
import java.io.Serializable;
import java.util.Objects;

//@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DeptEmployeesId implements Serializable {

    private long employees;
    private String department;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeptEmployeesId k = (DeptEmployeesId) o;
//        if (!Objects.equals(department, k.department)) return false;
//        return employees == k.employees;
        return employees == k.employees && Objects.equals(department, k.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(department, employees);
    }

//    @Override
//    public String toString() {
//        return "DeptEmployeesId{" +
//                "employees=" + employees +
//                "department=" + department +
//                '}';
//    }
}
