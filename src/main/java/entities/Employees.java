package entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import lombok.*;

import CustomEnum.Gender;

@Entity
@Table(name="employees")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@ToString
public class Employees {
    @Id
    @Column(name = "emp_no")
    private int empNo;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "hire_date")
    private LocalDate hireDate;

    @OneToMany(mappedBy = "employees")
    private List<Salaries> salaries;
    @OneToMany(mappedBy = "employees")
    private List<Titles> titles;

//    public Employee() {}
//    public Employee(int emp_no) {
//        this.emp_no = emp_no;
//    }
//
//    public int getEmp_no() {
//        return this.emp_no;
//    }
//    public void setEmp_no(int emp_no) {
//        this.emp_no = emp_no;
//    }
//
//    public LocalDate getBirth_date() {
//        return this.birth_date;
//    }
//    public void setEmp_no(LocalDate birth_date) {
//        this.birth_date = birth_date;
//    }
//
//    public String getFirst_name() {
//        return this.first_name;
//    }
//    public void setLast_name(String last_name) {
//        this.last_name = last_name;
//    }
//
//    public Gender getGender() {
//        return this.gender;
//    }
//    public void setGender(Gender gender) {
//        this.gender = gender;
//    }
//
//    public LocalDate getHire_date() {
//        return this.hire_date;
//    }
//    public void setHire_date(LocalDate hire_date) {
//        this.hire_date = hire_date;
//    }
//
    @Override
    public String toString() {
        return "Employee { " + "empNo = " + this.empNo + ", name = " + this.firstName + " " + this.lastName
                + ", birthDate = " + this.birthDate + ", gender = " + this.gender
                + ", hireDate = " + this.hireDate + '}';
    }
}