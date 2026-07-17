package com.companyservice.companyservice.dto;


import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyResponseDto {

    private String id;

    private String companyName;

    private String email;

    private String phone;

    private String website;

    private String location;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}