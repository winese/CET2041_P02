//package entities;
//
//import java.io.Serializable;
//import java.time.LocalDate;
//import java.util.Objects;
//
//import jakarta.persistence.*;
//import lombok.*;
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//@Embeddable
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@ToString
//public class TitleId implements Serializable {
//    @Column(name = "emp_no")
//    @JsonIgnore
//    private int employees;
//    @Column(name = "title")
//    private String title;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
//    @Column(name = "from_date")
//    private LocalDate fromDate;
//}
//
package entities;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

//@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TitleId implements Serializable {
    private long employees;
    private String title;
    private LocalDate fromDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TitleId t = (TitleId) o;
        if (!Objects.equals(title, t.title)) return false;
        if (!Objects.equals(fromDate, t.fromDate)) return false;
        return employees == t.employees;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employees, title, fromDate);
    }

//    @Override
//    public String toString() {
//        return "TitleId{" +
//                "employees=" + employees +
//                "title=" + title +
//                "fromDate=" + fromDate +
//                '}';
//    }
}