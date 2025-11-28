package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name="titles")
public class Titles {
    @Id private int emp_no;
    private String title;
    private LocalDate from_date;
    private LocalDate to_date;

    public Titles() {}
    public Titles(int emp_no) {
        this.emp_no = emp_no;
    }

    public int getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(int emp_no) {
        this.emp_no = emp_no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getFrom_date() {
        return from_date;
    }

    public void setFrom_date(LocalDate from_date) {
        this.from_date = from_date;
    }

    public LocalDate getTo_date() {
        return to_date;
    }

    public void setTo_date(LocalDate to_date) {
        this.to_date = to_date;
    }

    @Override
    public String toString() {
        return emp_no + " : " +
                "Title:" + title + ", " +
                "From " + from_date + ", To " + to_date;
    }
}
