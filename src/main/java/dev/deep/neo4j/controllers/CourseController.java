package dev.deep.neo4j.controllers;

import dev.deep.neo4j.entities.Course;
import dev.deep.neo4j.objects.CourseDTO;
import dev.deep.neo4j.services.CourseEnrolmentService;
import dev.deep.neo4j.services.CourseService;
import dev.deep.neo4j.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private LessonService lessonService;

    @Autowired
    private CourseEnrolmentService courseEnrolmentService;

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses(Principal principal) {
        List<Course> allCourses = courseService.getAllCourses();

        List<CourseDTO> response = allCourses.stream().map((course) -> CourseDTO.builder()
                .identifier(course.getIdentifier())
                .title(course.getTitle())
                .teacher(course.getTeacher())
                .lessons(lessonService.getAllLessonsByCourse(course.getIdentifier()))
                .isEnrolled(principal != null
                        ? courseEnrolmentService.getEnrolmentStatus(principal.getName(), course.getIdentifier())
                        : false)
                .build()).collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{identifier}")
    public ResponseEntity<CourseDTO> getCourse(@PathVariable String identifier) {
        Course course = courseService.getCourse(identifier);

        CourseDTO courseDTO = CourseDTO.builder()
                .identifier(course.getIdentifier())
                .title(course.getTitle())
                .teacher(course.getTeacher())
                .lessons(lessonService.getAllLessonsByCourse(identifier))
                .build();
        return new ResponseEntity<>(courseDTO, HttpStatus.OK);
    }
}
