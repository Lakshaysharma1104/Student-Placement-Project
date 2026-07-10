package com.example.studentplacement.repository;

import com.example.studentplacement.entity.Student;
import com.example.studentplacement.service.StudentDetailsService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,String> {


//    boolean findByEmail(String email);

    boolean existsByEmail( String email);
}
