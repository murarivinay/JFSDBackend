package com.university.Sustainable_Living_education.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String message) {
        super(message);
    }

    public StudentNotFoundException(Long id) {
        super("Student with ID " + id + " not found.");
    }
}
