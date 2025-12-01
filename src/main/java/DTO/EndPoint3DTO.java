package DTO;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EndPoint3DTO {
    private int empNo;
    private String firstName;
    private String lastName;
    private LocalDate hireDate;
}
