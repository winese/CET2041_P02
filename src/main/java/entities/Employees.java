package entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.List;

import CustomEnum.Gender;

/**
 * Employees entity class
 */
@Entity
@Table(name="employees")
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(
        name = "Employees.endPoint3",
        query = "select new DTO.EndPoint3DTO(" +
                "e.empNo, e.firstName, e.lastName, e.hireDate)" +
                "from Employees e " +
                "JOIN e.deptEmployees de " +
                "JOIN de.department d " +
                "WHERE d.deptNo = :deptNo"
)
@Getter
@Setter
@ToString
public class Employees {
    /**
     * Variable for employee number column
     */
    @Id
    @Column(name = "emp_no")
    private long empNo;
    /**
     * Variable for birth date
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "birth_date")
    private LocalDate birthDate;
    /**
     * Variable for first name column
     */
    @Column(name = "first_name")
    private String firstName;
    /**
     * Variable for last name column
     */
    @Column(name = "last_name")
    private String lastName;
    /**
     * Variable for gender column
     */
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    /**
     * Variable for hire date column
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "hire_date")
    private LocalDate hireDate;

    /**
     * Variable for relationship: 1 employees - many salaries
     */
    @OneToMany(mappedBy = "employees")
    @JsonManagedReference
    private List<Salaries> salaries;
    /**
     * Variable for relationship: 1 employees - many titles
     */
    @OneToMany(mappedBy = "employees")
    @JsonManagedReference
    private List<Titles> titles;
    /**
     * Variable for relationship: 1 employees - many deptEmployees
     */
    @OneToMany(mappedBy = "employees")
    @JsonManagedReference
    private List<DeptEmployees> deptEmployees;
    /**
     * Variable for relationship: 1 employees - many deptManager
     */
    @OneToMany(mappedBy = "employees")
    @JsonManagedReference
    private List<DeptManager> deptManager;
}