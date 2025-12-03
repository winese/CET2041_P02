package DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

/**
 * Data transfer object.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EndPoint3DTO {

    /**
     * Employee Number.
     */
    private long empNo;

    /**
     * Employee's first name.
     */
    private String firstName;

    /**
     * Employee's last name.
     */
    private String lastName;

    /**
     * Employee's hire date.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate hireDate;
}
