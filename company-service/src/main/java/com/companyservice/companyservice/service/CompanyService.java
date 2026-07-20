package com.companyservice.companyservice.service;

import com.companyservice.companyservice.Enum.Role;
import com.companyservice.companyservice.dto.CompanyRequestDto;
import com.companyservice.companyservice.dto.CompanyResponseDto;
import com.companyservice.companyservice.dto.Login;
import com.companyservice.companyservice.entity.Company;
import com.companyservice.companyservice.repository.CompanyRepository;
import com.companyservice.companyservice.security.SecurityConfig;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CompanyService {

    private final SecurityConfig securityConfig;
    private final AuthenticationManager authenticationManager;
    private final CompanyRepository repository;
    private final UserDetailServiceImp userDetailsService;
    private final JWTService jWTService;


    public  CompanyResponseDto getCompanyDetails(String id) {
          Company company = repository.findById(id)
                  .orElseThrow(()->new RuntimeException("company not found"));

        return convertToResponseDto(company);
    }
    public CompanyResponseDto convertToResponseDto(Company data){
        CompanyResponseDto companyResponse = new CompanyResponseDto();
        companyResponse.setId(data.getId());
        companyResponse.setCompanyName(data.getCompanyName());
        companyResponse.setEmail(data.getEmail());
        companyResponse.setPhone(data.getPhone());
        companyResponse.setDescription(data.getDescription());
        companyResponse.setLocation(data.getLocation());
        companyResponse.setWebsite(data.getWebsite());
        companyResponse.setCreatedAt(data.getCreatedAt());
        companyResponse.setUpdatedAt(data.getUpdatedAt());
        return  companyResponse;

    }

    public  CompanyResponseDto registerCompany(CompanyRequestDto data) {
        if (repository.existsByEmail(data.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        Company company = new Company();

            company.setCompanyName(data.getCompanyName());
            company.setEmail(data.getEmail());
            company.setPhone(data.getPhone());
            company.setDescription(data.getDescription());
            company.setLocation(data.getLocation());
            company.setWebsite(data.getWebsite());
            company.setRole(Role.ROLE_COMPANY);
            company.setPassword(securityConfig.passwordEncoder().encode(data.getPassword()));


          repository.save(company);
        return convertToResponseDto(company);
    }

    public @Nullable List<CompanyResponseDto> getAllCompanies() {
        List<Company> companies = repository.findAll();

        return companies.stream()
                  .map(this::convertToResponseDto)
                  .toList();
    }

    public  CompanyResponseDto updateDetails( CompanyRequestDto data,String id) {
        Company company= repository.findById(id)
                .orElseThrow(() -> new RuntimeException("company not found"));

        company.setPhone(data.getPhone());
        company.setDescription(data.getDescription());
        company.setLocation(data.getLocation());
        company.setWebsite(data.getWebsite());
        company.setPassword(data.getPassword());


        repository.save(company);
        return convertToResponseDto(company);
    }

    public void deleteCompany(String id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException("Company not found");
        }

        repository.deleteById(id);
    }

    public String companyLogin(@Valid Login data) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        data.getEmail(),
                        data.getPassword()
                )
        );
        UserDetails userDetails =  userDetailsService.loadUserByUsername(data.getEmail());
        return jWTService.generateToken(userDetails.getUsername());

    }
}
