package entities;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;

@Entity
@Table(name="dept_emp")
@IdClass(DeptEmployeesId.class)
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
    @NamedQuery(name = "DeptEmployees.findLatestDeptByEmpNo",
            query = "SELECT d FROM DeptEmployees d " +
                    "WHERE d.employees = :emp AND d.toDate > CURRENT_DATE")
})
@Getter
@Setter
@ToString
public class DeptEmployees {
//    @Id
//    @Column(name="emp_no")
//    @JsonIgnore
//    private int employees;
//    @Id
//    @JsonIgnore
//    @Column(name="dept_no")
//    private String department;
    @Id
    @ManyToOne
//    @MapsId ("employees")
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no")
    @JsonIgnore
    private Employees employees;
    @Id
    @ManyToOne
//    @MapsId ("department")
    @JoinColumn(name="dept_no", referencedColumnName = "dept_no")
    private Departments department;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name="from_date")
    private LocalDate fromDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name="to_date")
    private LocalDate toDate;

//    @JoinColumn(name = "")
//    private LocalDate date;

//    @Override
//    public String toString() {
//        return "DeptEmployees{" +
//                "employees=" + employees +
//                "department=" + department +
//                ", fromDate='" + fromDate +
//                ", toDate=" + toDate +
//                '}';
//    }
}