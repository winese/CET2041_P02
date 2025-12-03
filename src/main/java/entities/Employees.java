package entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.List;

import CustomEnum.Gender;

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
//@ToString
public class Employees {
    @Id
    @Column(name = "emp_no")
    private int empNo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "hire_date")
    private LocalDate hireDate;

    @OneToMany(mappedBy = "employees")
    @JsonManagedReference
    private List<Salaries> salaries;
    @OneToMany(mappedBy = "employees")
    @JsonManagedReference
    private List<Titles> titles;
    @OneToMany(mappedBy = "employees")
    @JsonManagedReference
    private List<DeptEmployees> deptEmployees;
    @OneToMany(mappedBy = "employees")
    @JsonManagedReference
    private List<DeptManager> deptManager;

    @Override
    public String toString() {
        return "Employee{" +
                "empNo=" + empNo +
                "birthDate=" + birthDate +
                ", firstName='" + firstName + ' ' +
                ", lastName='" + lastName + ' ' +
                ", gender='" + gender +
                ", hireDate=" + hireDate +
                '}';
    }
}