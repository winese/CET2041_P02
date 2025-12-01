package entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="dept_emp")
@IdClass(DeptEmployeesId.class)
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(
        name = "Employees.endPoint3",
        query = "SELECT new DTO.EndPoint3DTO(" +
                "e.empNo, e.firstName, e.lastName, e.hireDate)" +
                "FROM DeptEmployees de " +
                "JOIN de.employees e " +
                "WHERE de.deptNo = :deptNo"
)
@Getter
@Setter
@ToString
public class DeptEmployees {
    @Id
    @Column(name="emp_no")
    @JsonIgnore
    private int empNo;
    @Id
    @Column(name="dept_no")
    @JsonIgnore
    private String deptNo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name="from_date")
    private LocalDate fromDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name="to_date")
    private LocalDate toDate;
    @ManyToOne
    @MapsId ("empNo")
    @JoinColumn(name = "emp_no")
    @JsonIgnore
    private Employees employees;
    @ManyToOne
    @MapsId ("deptNo")
    @JoinColumn(name="dept_no")
    private Departments dept;
}