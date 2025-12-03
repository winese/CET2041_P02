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
public class DeptEmployeesId implements Serializable {

    private int empNo;
    private String deptNo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeptEmployeesId k = (DeptEmployeesId) o;
        if (!Objects.equals(deptNo, k.deptNo)) return false;
        return empNo == k.empNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(deptNo, empNo);
    }

    @Override
    public String toString() {
        return "DeptEmployeesId{" +
                "empNo=" + empNo +
                "deptNo=" + deptNo +
                '}';
    }
}
