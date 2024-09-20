package dev.deep.neo4j.services;

import dev.deep.neo4j.entities.Lesson;
import dev.deep.neo4j.repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    public List<Lesson> getAllLessonsByCourse(String identifier) {
        return lessonRepository.findLessonsByCourseIdentifier(identifier);
    }
}
