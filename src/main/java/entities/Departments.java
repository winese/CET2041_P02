package entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="departments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Departments {
    @Id
    @Column(name="dept_no")
    private String deptNo;
    @Column(name="dept_name")
    private String deptName;

    @OneToMany(mappedBy = "department")
    private List<DeptEmployees> deptEmployees;

    @Override
    public String toString() {
        return "Departments{" +
                "deptNo=" + deptNo +
                ", deptName='" + deptName +
                '}';
    }
}