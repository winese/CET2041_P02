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
    @Column(name="dept_no")
    private String deptNo;
    @Column(name="dept_name")
    private String deptName;

}