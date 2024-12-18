package com.university.Sustainable_Living_education.service;

import com.university.Sustainable_Living_education.model.Lesson;
import com.university.Sustainable_Living_education.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    // Save lesson
    public Lesson saveLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    // Get all lessons
    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }
    public Lesson getLessonById(Long id) {
        return lessonRepository.findById(id).orElse(null);  // Find lesson by ID
    }

    public void deleteLesson(Long id) {
        lessonRepository.deleteById(id);  // Delete lesson by ID
    }
 }

