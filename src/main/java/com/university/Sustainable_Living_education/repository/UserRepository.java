package com.university.Sustainable_Living_education.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.university.Sustainable_Living_education.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    // Custom query method to find a user by email, password, and role
    public abstract User findByEmailAndPasswordAndRole(String email, String password, String role);
}
