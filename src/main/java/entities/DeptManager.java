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
                query = "SELECT m FROM DeptManager m WHERE m.empNo = :empNo AND m.deptNo = :deptNo")
})
@Getter
@Setter
@ToString
public class DeptManager {
    @Id
    @JsonIgnore
    @Column(name="emp_no")
    private int empNo;
    @Id
    @JsonIgnore
    @Column(name="dept_no")
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
    private Departments department;

    @Override
    public String toString() {
        return "DeptManager{" +
                "empNo=" + empNo +
                "deptNo=" + deptNo +
                ", fromDate='" + fromDate +
                ", toDate=" + toDate +
                '}';
    }
}