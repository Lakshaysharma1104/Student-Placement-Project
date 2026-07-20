package com.companyservice.companyservice.service;

import com.companyservice.companyservice.entity.Company;
import com.companyservice.companyservice.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailServiceImp implements UserDetailsService {
    public final CompanyRepository repository;
    @Override
    @NullMarked
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Company  company  = repository.findByEmail(username);
        if(company != null){
            return org.springframework.security.core.userdetails.User
                    .builder()
                    .username(company.getEmail())
                    .password(company.getPassword())
                    .roles("COMPANY")
                    .build();
        }
        throw new UsernameNotFoundException(username);
    }
}
