package dev.deep.neo4j.services;

import dev.deep.neo4j.entities.Course;
import dev.deep.neo4j.queryresults.CourseEnrolmentQueryResult;
import dev.deep.neo4j.repositories.CourseRepository;
import dev.deep.neo4j.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseEnrolmentService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    public Boolean getEnrolmentStatus(String username, String identifier) {
        return userRepository.findEnrolmentStatus(username, identifier);
    }

    public CourseEnrolmentQueryResult enrollIn(String username, String identifier) {
        List<Course> courses = courseRepository.findAllEnrolledCoursesByUsername(username);
        long count = courses.stream().filter((course) -> course.getIdentifier().equals(identifier)).count();

        if (count != 0) return null;
        return userRepository.createEnrolmentRelationship(username, identifier);
    }

    public List<Course> getAllEnrolledCoursesByUsername(String username) {
        return courseRepository.findAllEnrolledCoursesByUsername(username);
    }
}
