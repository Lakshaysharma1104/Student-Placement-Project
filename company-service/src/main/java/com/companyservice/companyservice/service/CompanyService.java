package com.companyservice.companyservice.service;

import com.companyservice.companyservice.dto.CompanyRequestDto;
import com.companyservice.companyservice.dto.CompanyResponseDto;
import com.companyservice.companyservice.entity.Company;
import com.companyservice.companyservice.repository.CompanyRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

//    public CompanyResponseDto convertReqToResponseDto(CompanyRequestDto data) {
//        CompanyResponseDto company = new CompanyResponseDto();
//
//        company.setCompanyName(data.getCompanyName());
//        company.setEmail(data.getEmail());
//        company.setPhone(data.getPhone());
//        company.setDescription(data.getDescription());
//        company.setLocation(data.getLocation());
//        company.setWebsite(data.getWebsite());
////        company.setPassword(data.getPassword());
//        return company;
//    }

    public @Nullable List<CompanyResponseDto> getAllCompanies() {
        List<Company> companies = repository.findAll();

        return companies.stream()
                  .map(this::convertToResponseDto)
                  .toList();
    }

    public  CompanyResponseDto updateDetails( CompanyRequestDto data,String id) {
        Company company= repository.findById(id)
                .orElseThrow(() -> new RuntimeException("company not found"));


//        company.setCompanyName(data.getCompanyName());
//        company.setEmail(data.getEmail());
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
}
