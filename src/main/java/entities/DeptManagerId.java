package entities;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DeptManagerId implements Serializable {

    private int empNo;
    private String deptNo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeptManagerId k = (DeptManagerId) o;
        if (!Objects.equals(deptNo, k.deptNo)) return false;
        return empNo == k.empNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(deptNo, empNo);
    }
}
