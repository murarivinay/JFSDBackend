package com.university.Sustainable_Living_education.service;

import com.university.Sustainable_Living_education.model.Message;
import com.university.Sustainable_Living_education.model.Student;  // Add the Student import
import com.university.Sustainable_Living_education.repository.MessageRepository;
import com.university.Sustainable_Living_education.repository.StudentRepository;  // Add the StudentRepository import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private StudentRepository studentRepository;  // Inject the StudentRepository to fetch student data

    // Send a message to a student
    public Message sendMessage(Long studentId, String content) {
        // Fetch the student by their ID
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));

        // Create a new message
        Message message = new Message();
        message.setContent(content);
        message.setStudent(student);  // Set the entire student entity, not just the ID
        message.setCreatedAt(LocalDateTime.now());

        // Save and return the message
        return messageRepository.save(message);
    }

    // Get all messages for a student
    public List<Message> getMessages(Long studentId) {
        return messageRepository.findByStudentId(studentId);
    }

    // Mark a message as read
    public void markAsRead(Long messageId) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("Message not found with id: " + messageId));
        message.setRead(true);
        messageRepository.save(message);
    }
}
