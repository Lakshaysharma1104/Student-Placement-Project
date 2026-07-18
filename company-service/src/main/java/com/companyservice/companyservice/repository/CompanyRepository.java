package com.companyservice.companyservice.repository;

import com.companyservice.companyservice.entity.Company;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,String> {
    boolean existsByEmail(@Email(message = "Invalid email format") @NotBlank(message = "Email is required") String email) ;

    boolean existsByCompanyName(@NotBlank(message = "Company name is required") String companyName);
}
