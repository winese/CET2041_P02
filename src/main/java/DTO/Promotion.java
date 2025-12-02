package DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Promotion {
    private int empNo;
    private int raise;
    private String title;
    private String deptNo;
    private boolean isManager = false;
}