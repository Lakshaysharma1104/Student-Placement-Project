package com.example.studentplacement.service;

import com.example.studentplacement.entity.Student;
import com.example.studentplacement.repository.StudentRepository;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final StudentRepository studentRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         Student student = studentRepository.findByEmail(username);
         if(student!=null){
             return org.springframework.security.core.userdetails.User
                     .builder()
                     .username(student.getEmail())
                     .password(student.getPassword())
                     .roles("USER")
                     .build();
         }
        throw new UsernameNotFoundException("username not found!!");
    }
}
