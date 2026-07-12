package com.example.studentplacement.dto;

import com.example.studentplacement.Enum.Branch;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class StudentRegistrationDto {

    @NotBlank(message = "Roll Number cannot be empty")
    private String  rollNo;

    @NotBlank(message = "Name cannot be empty")
    private String studentName;

    @NotNull(message = "Semester is required")
    @Min(value = 1, message = "Semester must be at least 1")
    @Max(value = 8, message = "Semester cannot be greater than 8")
    private Integer semester;

    @NotNull(message = "Branch is required")
    private Branch branch;
    private Double cgpa;
    @Email(message = "Enter a Valid email")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Enter a password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;



}
