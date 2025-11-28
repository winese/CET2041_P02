package entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SalaryId implements Serializable {
    @Column(name = "emp_no")
    private int empNo;

    @Column(name = "from_date")
    private LocalDate fromDate;
}