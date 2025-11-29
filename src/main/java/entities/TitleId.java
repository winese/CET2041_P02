package entities;

import java.io.Serializable;
import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TitleId implements Serializable {
    @Column(name = "emp_no")
    @JsonIgnore
    private int empNo;
    @Column(name = "title")
    private String title;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "from_date")
    private LocalDate fromDate;
}