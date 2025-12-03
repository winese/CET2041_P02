package entities;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;

/**
 * Entity for Department Mangers, mapped to "dept_managers" table.
 */
@Entity
@Table(name="dept_manager")
@IdClass(DeptManagerId.class)
@AllArgsConstructor
@NoArgsConstructor
/**
 * Named Query for finding the department manager given the department
 * and employee number.
 */
@NamedQueries({
        @NamedQuery(name = "DeptManager.findDeptManagerByEmpNo",
                query = "SELECT m FROM DeptManager m WHERE m.employees = " +
                        ":emp AND m.department = :newDept")
})
@Getter
@Setter
@ToString
public class DeptManager {

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
     * Starting date for a duration where a manager was the manager for that
     * department.
     * Formatted for JSON in dd-MM-yyyy format.
     * Mapped to "from_date" column.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name="from_date")
    private LocalDate fromDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name="to_date")
    private LocalDate toDate;
}