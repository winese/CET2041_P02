package entities;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import CustomEnum.Gender;

@Entity
@Table(name="employees")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
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
    private List<Salaries> salaries;
    @OneToMany(mappedBy = "employees")
    private List<Titles> titles;
    @OneToMany(mappedBy = "employees")
    private List<DeptEmployees> deptEmployees;
    @OneToMany(mappedBy = "employees")
    private List<DeptManager> deptManager;
}