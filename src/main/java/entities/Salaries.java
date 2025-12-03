package entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Salaries entity class
 */
@Entity
@Table(name="salaries")
@IdClass(SalaryId.class)
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(
        name = "Salaries.findLatestSalaryByEmpNo",
        query = "SELECT s FROM Salaries s " +
                "WHERE s.employees = :emp AND s.toDate > " +
                "CURRENT_DATE")
@Getter
@Setter
@ToString
public class Salaries {
    /**
     * Variable for salary column
     */
    @Column(name = "salary")
    private BigDecimal salary;
    /**
     * Variable for from date column
     */
    @Id
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name="from_date")
    private LocalDate fromDate;
    /**
     * Variable for to date column
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "to_date")
    private LocalDate toDate;

    /**
     * Variable for relationship: many salaries - 1 employees
     */
    @ManyToOne
    @MapsId ("employees")
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no")
    @JsonBackReference
    private Employees employees;
}