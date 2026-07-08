package com.example.studentplacement.dto;

import com.example.studentplacement.Enum.Branch;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class StudentRegistrationDto {
    private String id;
    private String  rollNo;
    private String studentName;
    private Integer semester;
    @Enumerated( EnumType.STRING )
    private Branch branch;
    private Double cgpa;
    @Email
    private String email;


}
