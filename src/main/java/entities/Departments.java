package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="departments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@ToString
public class Departments {
    @Id
    @Column(name="dept_no")
    private String deptNo;
    @Column(name="dept_name")
    private String deptName;

    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private List<DeptEmployees> deptEmployees;
    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private List<DeptManager> deptManager;

    @Override
    public String toString() {
        return "Departments{" +
                "deptNo=" + deptNo +
                ", deptName='" + deptName +
                '}';
    }
}