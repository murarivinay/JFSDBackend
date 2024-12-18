package com.university.Sustainable_Living_education.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.university.Sustainable_Living_education.model.User;
import com.university.Sustainable_Living_education.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")  // Allow requests from frontend
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginDetails) {
        String email = loginDetails.getEmail();
        String password = loginDetails.getPassword();
        String role = loginDetails.getRole();

        // Authenticate the user based on email, password, and role
        User user = userRepository.findByEmailAndPasswordAndRole(email, password, role);

        if (user != null) {
            // If user is authenticated, send a success message with the user's role
            Map<String, String> response = new HashMap<>();
            response.put("message", "Login successful!");
            response.put("role", user.getRole());
            return ResponseEntity.ok(response);
        } else {
            // If authentication fails, send an Unauthorized status with a JSON error message
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Invalid credentials or role.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(errorResponse);
        }
    }
}
