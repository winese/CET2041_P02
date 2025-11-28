package entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="departments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Departments {
    @Id
    @Column(name="dept_name")
    private String deptName;
    @Column(name="dept_no")
    private String deptNo;

//    public Departments() {
//    }
//
//    public Departments(String dept_no, String dept_name) {
//        this.dept_no = dept_no;
//        this.dept_name = dept_name;
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
//    public String getDept_name() {
//        return dept_name;
//    }
//    public void setDept_name(String dept_name) {
//        this.dept_name = dept_name;
//    }
//
//    @Override
//    public String toString(){
//        return "dept_no = " + dept_no +
//                ", dept_name = " + dept_name;
//    }
}