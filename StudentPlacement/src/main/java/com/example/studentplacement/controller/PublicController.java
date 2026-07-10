package com.example.studentplacement.controller;

import com.example.studentplacement.dto.LoginDto;
import com.example.studentplacement.dto.StudentRegistrationDto;
import com.example.studentplacement.service.StudentDetailsService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
@AllArgsConstructor
public class PublicController {

    public StudentDetailsService studentService;

    @PostMapping("/login")
    public ResponseEntity<LoginDto> studentLogin(@Valid @RequestBody LoginDto data){
        return  ResponseEntity.ok(studentService.studentLogin(data));
    }

    @PostMapping("/register")
    public ResponseEntity<StudentRegistrationDto> studentRegister(@Valid @RequestBody StudentRegistrationDto data){
        return  ResponseEntity.ok(studentService.saveDetails(data));
    }

}
