package entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;

@Entity
@Table(name="salaries")
@IdClass(SalaryId.class)
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Salaries.findLatestSalaryByEmpNo",
                query = "SELECT s FROM Salaries s " +
                        "WHERE s.employees = :emp AND s.toDate > " +
                        "CURRENT_DATE")
})
@Getter
@Setter
//@ToString
public class Salaries {
//    @Id
//    @JsonIgnore
//    @JoinColumn(name="emp_no")
//    private int employees;
    @Column(name = "salary")
    private int salary;
    @Id
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name="from_date")
    private LocalDate fromDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "to_date")
    private LocalDate toDate;

    @ManyToOne
    @MapsId ("employees")
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no")
    @JsonBackReference
    private Employees employees;

    @Override
    public String toString() {
        return "Salaries{" +
//                "employees=" + employees +
                "salary=" + salary +
                ", fromDate='" + fromDate +
                ", toDate='" + toDate +
                '}';
    }
}