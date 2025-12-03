package entities;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;

/**
 * Entity for Department Employees, mapped to "dept_emp" table.
 */
@Entity
@Table(name="dept_emp")
@IdClass(DeptEmployeesId.class)
@AllArgsConstructor
@NoArgsConstructor
/**
 * Named Query for finding the current department that an employee is in.
 */
@NamedQueries({
    @NamedQuery(name = "DeptEmployees.findLatestDeptByEmpNo",
            query = "SELECT d FROM DeptEmployees d " +
                    "WHERE d.employees = :emp AND d.toDate > CURRENT_DATE")
})
@Getter
@Setter
@ToString
public class DeptEmployees {

    /**
     * Employee number. Composite key.
     * Maps to "emp_no" column.
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no")
    @JsonIgnore
    private Employees employees;

    /**
     * Department number. Composite key.
     * Maps to "dept_no" table.
     */
    @Id
    @ManyToOne
    @JoinColumn(name="dept_no", referencedColumnName = "dept_no")
    private Departments department;

    /**
     * Starting date for a duration where an employee was in a department.
     * Formatted for JSON in dd-MM-yyyy format.
     * Mapped to "from_date" column.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name="from_date")
    private LocalDate fromDate;

    /**
     * End date for a duration where an employee was in a department.
     * Formatted for JSON in dd-MM-yyyy format.
     * Set to year 9999 for an employee's current allocation to a department
     * Mapped to "from_date" column.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name="to_date")
    private LocalDate toDate;

}