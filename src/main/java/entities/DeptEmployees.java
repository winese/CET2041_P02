package entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="dept_emp")
@IdClass(DeptEmployeesId.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DeptEmployees {
    @Id
    @Column(name="emp-no")
    private int empNo;
    @Id
    @Column(name="dept_no")
    private String deptNo;
    @Column(name="from_date")
    private LocalDate fromDate;
    @Column(name="to_date")
    private LocalDate toDate;
    @ManyToOne
    @JoinColumn(name="dept_no", referencedColumnName = "dept_no")
    private entities.Departments dept;

//    public DeptEmployees() {}
//    public DeptEmployees(int emp_no) {
//        this.emp_no = emp_no;
//    }
//
//    public int getEmp_no() {
//        return emp_no;
//    }
//
//    public void setEmp_no(int emp_no) {
//        this.emp_no = emp_no;
//    }
//
//    public String getDept_no() {
//        return dept_no;
//    }
//
//    public void setDept_no(String dept_no) {
//        this.dept_no = dept_no;
//    }
//
//    public LocalDate getFrom_date() {
//        return from_date;
//    }
//
//    public void setFrom_date(LocalDate from_date) {
//        this.from_date = from_date;
//    }
//
//    public LocalDate getTo_date() {
//        return to_date;
//    }
//
//    public void setTo_date(LocalDate to_date) {
//        this.to_date = to_date;
//    }
//
//    @Override
//    public String toString() {
//        return emp_no + " : " +
//                "dept_no = " + dept_no +
//                ", from_date = " + from_date +
//                ", to_date = " + to_date;
//    }
}