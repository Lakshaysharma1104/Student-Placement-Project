package com.example.studentplacement.service;

import com.example.studentplacement.dto.LoginDto;
import com.example.studentplacement.dto.StudentRegistrationDto;
import com.example.studentplacement.entity.Student;
import com.example.studentplacement.repository.StudentRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class StudentDetailsService {

    private StudentRepository studentRepository;

    public StudentRegistrationDto getStudentDetails(String id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return setDetails(student);
    }

    public StudentRegistrationDto setDetails(Student student){
        StudentRegistrationDto studentRegistration = new StudentRegistrationDto();
        studentRegistration.setStudentName(student.getName());
        studentRegistration.setEmail(student.getEmail());
        studentRegistration.setRollNo(student.getRollNo());
        studentRegistration.setCgpa(student.getCgpa());
        studentRegistration.setBranch(student.getBranch());
        studentRegistration.setSemester(student.getSemester());
        return studentRegistration;
    }

    public StudentRegistrationDto saveDetails(StudentRegistrationDto dto) {

        if(studentRepository.existsByEmail(dto.getEmail())){
            log.warn("student is already registered",new RuntimeException("student is already registered"));
        }

        Student student = new Student();
        student.setName(dto.getStudentName());
        student.setEmail(dto.getEmail());
        student.setRollNo(dto.getRollNo());
        student.setCgpa(dto.getCgpa());
        student.setSemester(dto.getSemester());
        student.setBranch(dto.getBranch());
        student.setPassword(dto.getPassword());

        Student savedStudent = studentRepository.save(student);

        return setDetails(savedStudent);
    }

    public @Nullable StudentRegistrationDto updateDetails(String id, StudentRegistrationDto student) {
        Student updateStu = studentRepository.findById(id)
                        .orElseThrow(()-> new RuntimeException("user not found!"));

        if (student.getStudentName() != null) {
            updateStu.setName(student.getStudentName());
        }

        if (student.getEmail() != null) {
            updateStu.setEmail(student.getEmail());
        }

        if (student.getRollNo() != null) {
            updateStu.setRollNo(student.getRollNo());
        }

        if (student.getBranch() != null) {
            updateStu.setBranch(student.getBranch());
        }

        if (student.getSemester() != null) {
            updateStu.setSemester(student.getSemester());
        }

        if (student.getCgpa() != null) {
            updateStu.setCgpa(student.getCgpa());
        }
        Student updatedStudent = studentRepository.save(updateStu);

        return  setDetails(updatedStudent);

    }

    public void deleteDetails(String id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Student not found!"));
        studentRepository.delete(student);
    }

    public @Nullable LoginDto studentLogin(@Valid LoginDto data) {
//        if(!studentRepository.findByEmail(data.getEmail())){
//            log.error("User is not registered",new RuntimeException("register first"));
//        }else{
//
//        }


      return null;

    }


}
