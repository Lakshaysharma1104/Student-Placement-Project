package com.companyservice.companyservice.service;

import com.companyservice.companyservice.dto.CompanyRequestDto;
import com.companyservice.companyservice.dto.CompanyResponseDto;
import com.companyservice.companyservice.entity.Company;
import com.companyservice.companyservice.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyService {

    private final CompanyRepository repository;
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
            company.setPassword(data.getPassword());


          repository.save(company);
        return convertToResponseDto(company);
    }
}
