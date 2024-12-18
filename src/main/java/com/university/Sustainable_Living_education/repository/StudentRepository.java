package com.university.Sustainable_Living_education.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.university.Sustainable_Living_education.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	Optional<Student> findByEmail(String email);
	boolean existsById(Long id);
}
