package com.companyservice.companyservice.controller;


import com.companyservice.companyservice.dto.CompanyRequestDto;
import com.companyservice.companyservice.dto.CompanyResponseDto;
import com.companyservice.companyservice.service.CompanyService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
@AllArgsConstructor
public class CompanyController {

    private CompanyService companyService;

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponseDto> getCompanyById(@PathVariable String id){
        return ResponseEntity.ok(companyService.getCompanyDetails(id));
    }
    @PostMapping("/register")
    public ResponseEntity<CompanyResponseDto> registerCompany(@RequestBody  @Valid  CompanyRequestDto data){
        return ResponseEntity.ok(companyService.setCompanyDetails(data));
    }

}
