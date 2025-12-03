package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.inject.Named;
import jakarta.persistence.*;
//import jdk.jfr.Name;
import lombok.*;

import java.util.List;

/**
 * Entity for Departments, mapped to "departments" table.
 */
@Entity
@Table(name="departments")
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name="Departments.getAllDepartments",
                    query="select d from Departments d"),
        @NamedQuery(name="Departments.getDeptNo",
                    query="select d.deptNo from Departments d " +
                            "where d.deptNo = :deptNo")
})
@Getter
@Setter
@ToString
public class Departments {

    /**
     *Department number. Primary key.
     *Maps to "dept_no" column.
     */
    @Id
    @Column(name="dept_no")
    private String deptNo;

    /**
     * Name of department.
     * Maps to column "dept_name".
     */
    @Column(name="dept_name")
    private String deptName;

    /**
     * One-to-Many relationship between departments and dept_emp
     */
    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private List<DeptEmployees> deptEmployees;

    /**
     * One-to-Many relationship between departments and dept_manager
     */
    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private List<DeptManager> deptManager;

}