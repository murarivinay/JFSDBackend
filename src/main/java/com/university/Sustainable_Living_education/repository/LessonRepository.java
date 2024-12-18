package com.university.Sustainable_Living_education.repository;

import com.university.Sustainable_Living_education.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
	
}
