package com.example.studentplacement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class LoginDto {
    @Email(message = "enter a correct mail")
    @NotBlank(message = "Email can not be blank")
    private String email;
    @NotBlank(message = "Enter a password")
    private String password;
}
