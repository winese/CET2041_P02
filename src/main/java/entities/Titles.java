package entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Titles entity class
 */
@Entity
@Table(name="titles")
@IdClass(TitleId.class)
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(
        name = "Titles.findLatestTitleByEmpNo",
        query = "SELECT t FROM Titles t " +
                "WHERE t.employees = :emp AND t.toDate > " +
                "CURRENT_DATE")
@Getter
@Setter
@ToString
public class Titles {
    /**
     * Variable for title column
     */
    @Id
    @Column(name="title")
    private String title;
    /**
     * Variable for from date column
     */
    @Id
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name="from_date")
    private LocalDate fromDate;
    /**
     * Variable for to date column
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "to_date")
    private LocalDate toDate;

    /**
     * Variable for relationship: many titles - 1 employees
     */
    @ManyToOne
    @MapsId ("employees")
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no")
    @JsonBackReference
    private Employees employees;
}