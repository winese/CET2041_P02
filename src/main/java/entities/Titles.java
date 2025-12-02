package entities;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="titles")
@IdClass(TitleId.class)
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Titles.findLatestTitleByEmpNo",
                query = "SELECT t FROM Titles t WHERE t.empNo = :empNo AND t.toDate > CURRENT_DATE")
})
@Getter
@Setter
@ToString
public class Titles {
    @Id
    @JsonIgnore
    @Column(name="emp_no")
    private int empNo;
    @Id
    @Column(name="title")
    private String title;
    @Id
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name="from_date")
    private LocalDate fromDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "to_date")
    private LocalDate toDate;

    @ManyToOne
    @MapsId ("empNo")
    @JoinColumn(name = "emp_no")
    @JsonIgnore
    private Employees employees;
}