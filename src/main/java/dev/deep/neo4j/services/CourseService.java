package dev.deep.neo4j.services;

import dev.deep.neo4j.entities.Course;
import dev.deep.neo4j.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourse(String identifier){
        return courseRepository
                .findCourseByIdentifier(identifier)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
    }
}
