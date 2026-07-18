package com.companyservice.companyservice.controller;


import com.companyservice.companyservice.dto.CompanyRequestDto;
import com.companyservice.companyservice.dto.CompanyResponseDto;
import com.companyservice.companyservice.service.CompanyService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
@AllArgsConstructor
public class CompanyController {

    private CompanyService companyService;

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponseDto> getCompanyById(@PathVariable String id){
        return ResponseEntity.ok(companyService.getCompanyDetails(id));
    }
    @GetMapping("/get-all-companies")
    public ResponseEntity<List<CompanyResponseDto>> getAllCompanies(){
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    @PostMapping("/register")
    public ResponseEntity<CompanyResponseDto> registerCompany(@RequestBody  @Valid  CompanyRequestDto data){
        return ResponseEntity.ok(companyService.registerCompany(data));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<CompanyResponseDto> updateCompany(@RequestBody   CompanyRequestDto data, @PathVariable String id){
        return ResponseEntity.ok(companyService.updateDetails(data,id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable String id) {

        companyService.deleteCompany(id);

        return ResponseEntity.ok("Company deleted successfully");
    }

}
