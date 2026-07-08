package com.example.studentplacement.entity;

import com.example.studentplacement.Enum.Branch;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;

    private String email;

    private String rollNo;

    @Enumerated(EnumType.STRING)
    private Branch branch;

    private Double cgpa;

    private Integer semester;

}
