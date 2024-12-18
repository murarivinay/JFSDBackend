package com.university.Sustainable_Living_education.controller;

import com.university.Sustainable_Living_education.model.Message;
import com.university.Sustainable_Living_education.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    // Send a message to a student
    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestParam Long studentId, @RequestBody String content) {
        try {
            // Send the message
            Message message = messageService.sendMessage(studentId, content);
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Get all messages for a student
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable Long studentId) {
        try {
            // Retrieve messages for the student
            List<Message> messages = messageService.getMessages(studentId);
            return ResponseEntity.ok(messages);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Mark a message as read
    @PatchMapping("/markAsRead/{messageId}")
    public ResponseEntity<Void> markAsRead(@PathVariable Long messageId) {
        try {
            messageService.markAsRead(messageId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
