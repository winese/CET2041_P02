package entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;

@Entity
@Table(name="titles")
@IdClass(TitleId.class)
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Titles.findLatestTitleByEmpNo",
                query = "SELECT t FROM Titles t " +
                        "WHERE t.employees = :emp AND t.toDate > " +
                        "CURRENT_DATE")
})
@Getter
@Setter
@ToString
public class Titles {
//    @Id
//    @JsonIgnore
//    @Column(name="emp_no", insertable = false, updatable = false)
//    private int employees;
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
    @MapsId ("employees")
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no")
    @JsonBackReference
    private Employees employees;

//    @Override
//    public String toString() {
//        return "Titles{" +
////                "employees=" + employees +
//                "title=" + title +
//                ", fromDate='" + fromDate +
//                ", toDate='" + toDate +
//                '}';
//    }
}