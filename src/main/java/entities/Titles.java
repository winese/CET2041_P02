package entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

@Entity
@Table(name="titles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Titles {
    @EmbeddedId
    private TitleId titleId;
    @Column(name = "to_date")
    private LocalDate toDate;

    @ManyToOne
    @MapsId ("empNo")
    @JoinColumn(name = "emp_no") // This specifies the foreign key column
    private Employees employees;

//    @Id private int emp_no;
//    private int salary;
//    private LocalDate from_date;
//    private LocalDate to_date;
//
//    public Salaries(){}
//    public Salaries(int emp_no) {
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
//    public int getSalary() {
//        return salary;
//    }
//
//    public void setSalary(int salary) {
//        this.salary = salary;
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

    @Override
    public String toString() {
        return "Titles { " + "empNo = " + this.titleId.getEmpNo() + ", title = " + this.titleId.getTitle()
                + ", fromDate = " + this.titleId.getFromDate() + ", toDate = " + this.toDate + '}';
//        return emp_no + " -> " +
//                "Salary: " + salary + ", " +
//                "From: " + from_date + ", " +
//                "To: " + to_date;
    }
}




//package entities;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//
//import java.time.LocalDate;
//
//@Entity
//@Table(name="titles")
//public class Titles {
//    @Id private int emp_no;
//    private String title;
//    private LocalDate from_date;
//    private LocalDate to_date;
//
//    public Titles() {}
//    public Titles(int emp_no) {
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
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
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
//                "Title:" + title + ", " +
//                "From " + from_date + ", To " + to_date;
//    }
//}
