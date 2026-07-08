package com.example.studentplacement.controller;

import com.example.studentplacement.dto.StudentRegistrationDto;
import com.example.studentplacement.service.StudentDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Students-Details")
@AllArgsConstructor
public class StudentController {

    private StudentDetailsService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<StudentRegistrationDto> getDetails(@PathVariable String id){
      return  new ResponseEntity<>(studentService.getStudentDetails(id),HttpStatus.OK);
    }
    @PostMapping("/save/students")
    public  ResponseEntity<StudentRegistrationDto> saveDetails(@RequestBody StudentRegistrationDto student){
        return ResponseEntity.ok(studentService.saveDetails(student));
    }
}
