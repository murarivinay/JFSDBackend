package com.university.Sustainable_Living_education.repository;

import com.university.Sustainable_Living_education.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    // Fetch all messages for a specific student
    List<Message> findByStudentId(Long studentId);
}
