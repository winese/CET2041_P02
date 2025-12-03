package entities;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;

@Entity
@Table(name="dept_manager")
@IdClass(DeptManagerId.class)
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "DeptManager.findDeptManagerByEmpNo",
                query = "SELECT m FROM DeptManager m WHERE m.employees = " +
                        ":emp AND m.department = :newDept")
})
@Getter
@Setter
@ToString
public class DeptManager {
//    @Id
//    @JsonIgnore
//    @Column(name="emp_no")
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


//    @Override
//    public String toString() {
//        return "DeptManager{" +
////                "employees=" + employees +
////                "department=" + department +
//                ", fromDate='" + fromDate +
//                ", toDate=" + toDate +
//                '}';
//    }
}