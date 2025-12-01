package entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="dept_manager")
@IdClass(DeptManagerId.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DeptManager {
    @Id
    @Column(name="emp_no")
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
    private Departments dept;

}