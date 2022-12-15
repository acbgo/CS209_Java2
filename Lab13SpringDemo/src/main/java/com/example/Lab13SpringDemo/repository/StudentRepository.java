package com.example.Lab13SpringDemo.repository;

import com.example.Lab13SpringDemo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByEmailLike(String email);
}
