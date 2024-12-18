package com.university.Sustainable_Living_education.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.university.Sustainable_Living_education.exception.StudentNotFoundException;
import com.university.Sustainable_Living_education.model.Student;
import com.university.Sustainable_Living_education.repository.StudentRepository;

@RestController
@CrossOrigin("http://localhost:3000")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // Create a new student
    @PostMapping("/student")
    public ResponseEntity<?> newStudent(@RequestBody Student newStudent) {
        if (studentRepository.findByEmail(newStudent.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Student with this email already exists.");
        }
        newStudent.setRole("Student"); // Set default role
        return ResponseEntity.status(HttpStatus.CREATED).body(studentRepository.save(newStudent));
    }

    // Get all students
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return ResponseEntity.ok(students);
    }

    // Get a student by ID
    @GetMapping("/student/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + id + " not found."));
        return ResponseEntity.ok(student);
    }

    // Update a student by ID
    @PutMapping("/student/{id}")
    public ResponseEntity<?> updateStudent(@RequestBody Student newStudent, @PathVariable Long id) {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setName(newStudent.getName());
                    student.setEmail(newStudent.getEmail());
                    student.setPassword(newStudent.getPassword());
                    return ResponseEntity.ok(studentRepository.save(student));
                }).orElseThrow(() -> new StudentNotFoundException("Student with ID " + id + " not found."));
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        try {
            if (!studentRepository.existsById(id)) {
                throw new StudentNotFoundException("Student with ID " + id + " not found.");
            }
            studentRepository.deleteById(id);
            return ResponseEntity.ok("Student with ID " + id + " has been successfully deleted.");
        } catch (Exception e) {
            // Log the error
            System.err.println("Error while deleting student with ID: " + id);
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete student. Please check server logs for more details.");
        }
    }


    // Student login endpoint
    @PostMapping("/student/login")
    public ResponseEntity<String> loginStudent(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            return ResponseEntity.badRequest().body("Email and Password must be provided.");
        }

        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new StudentNotFoundException("Invalid email or password."));

        if (student.getPassword().equals(password)) {
            return ResponseEntity.ok("Login Successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password.");
        }
    }
}
