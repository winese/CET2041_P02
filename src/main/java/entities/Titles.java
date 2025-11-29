package entities;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="titles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Titles {
    @EmbeddedId
    private TitleId titleId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "to_date")
    private LocalDate toDate;

    @ManyToOne
    @MapsId ("empNo")
    @JoinColumn(name = "emp_no")
    @JsonIgnore
    private Employees employees;
}