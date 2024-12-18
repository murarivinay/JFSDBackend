package com.university.Sustainable_Living_education.controller;

import com.university.Sustainable_Living_education.model.Lesson;
import com.university.Sustainable_Living_education.repository.LessonRepository;
import com.university.Sustainable_Living_education.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000") 
@RequestMapping("/lessons")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    // Endpoint to add a lesson
    @PostMapping("/add")
    public ResponseEntity<Lesson> addLesson(@RequestBody Lesson lesson) {
        Lesson savedLesson = lessonService.saveLesson(lesson);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLesson);
    }

    // Endpoint to get all lessons
    @GetMapping
    public List<Lesson> getAllLessons() {
        return lessonService.getAllLessons();
    }
}
