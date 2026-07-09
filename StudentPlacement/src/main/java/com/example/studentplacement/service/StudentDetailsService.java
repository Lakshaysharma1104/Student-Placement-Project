package com.example.studentplacement.service;

import com.example.studentplacement.dto.StudentRegistrationDto;
import com.example.studentplacement.entity.Student;
import com.example.studentplacement.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

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
        studentRegistration.setId(student.getId());
        studentRegistration.setStudentName(student.getName());
        studentRegistration.setEmail(student.getEmail());
        studentRegistration.setRollNo(student.getRollNo());
        studentRegistration.setCgpa(student.getCgpa());
        studentRegistration.setBranch(student.getBranch());
        studentRegistration.setSemester(student.getSemester());
        return studentRegistration;
    }

    public StudentRegistrationDto saveDetails(StudentRegistrationDto dto) {

        Student student = new Student();
        student.setId(dto.getId());
        student.setName(dto.getStudentName());
        student.setEmail(dto.getEmail());
        student.setRollNo(dto.getRollNo());
        student.setCgpa(dto.getCgpa());
        student.setSemester(dto.getSemester());
        student.setBranch(dto.getBranch());

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
}
