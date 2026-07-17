package com.example.studentplacement.controller;

import com.example.studentplacement.dto.StudentRegistrationDto;
import com.example.studentplacement.service.StudentDetailsService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("student-details")
@AllArgsConstructor
public class StudentController {

    private StudentDetailsService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<StudentRegistrationDto> getDetails(@PathVariable String id){
      return  new ResponseEntity<>(studentService.getStudentDetails(id),HttpStatus.OK);
    }
//    @PostMapping("/save/students")
//    public  ResponseEntity<StudentRegistrationDto> saveDetails(@Valid  @RequestBody StudentRegistrationDto student){
//        return ResponseEntity.ok(studentService.saveDetails(student));
//    }
    @PutMapping("update/{id}")
    public ResponseEntity<StudentRegistrationDto> updateDetails(@PathVariable String id,@Valid @RequestBody StudentRegistrationDto student){
        return ResponseEntity.ok(studentService.updateDetails(id,student));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteDetails(@PathVariable String id){
        studentService.deleteDetails(id);
        return ResponseEntity.noContent().build();
    }
}
