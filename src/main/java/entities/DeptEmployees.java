package entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="dept_emp")
@IdClass(DeptEmployeesId.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DeptEmployees {
    @Id
    @Column(name="emp_no")
    private int empNo;
    @Id
    @Column(name="dept_no")
    private String deptNo;
    @Column(name="from_date")
    private LocalDate fromDate;
    @Column(name="to_date")
    private LocalDate toDate;
    @ManyToOne
    @JoinColumn(name="emp_no", referencedColumnName = "emp_no")
    private entities.Employees employee;
    @ManyToOne
    @JoinColumn(name="dept_no", referencedColumnName = "dept_no")
    private entities.Departments dept;
}