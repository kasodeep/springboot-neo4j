package dev.deep.neo4j.controllers;

import dev.deep.neo4j.entities.Course;
import dev.deep.neo4j.objects.CourseDTO;
import dev.deep.neo4j.objects.CourseEnrolmentDTO;
import dev.deep.neo4j.queryresults.CourseEnrolmentQueryResult;
import dev.deep.neo4j.requests.CourseEnrolmentRequest;
import dev.deep.neo4j.services.CourseEnrolmentService;
import dev.deep.neo4j.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/enrolments")
public class CourseEnrolmentController {

    @Autowired
    private CourseEnrolmentService courseEnrolmentService;

    @Autowired
    private LessonService lessonService;

    @PostMapping
    public ResponseEntity<?> enrollIn(@RequestBody CourseEnrolmentRequest request, Principal principal) {
        CourseEnrolmentQueryResult enrolmentQueryResult = courseEnrolmentService
                .enrollIn(principal.getName(), request.getCourseIdentifier());

        if(enrolmentQueryResult == null){
            return new ResponseEntity<>("User already enrolled", HttpStatus.OK);
        }

        CourseEnrolmentDTO courseEnrolmentDTO = CourseEnrolmentDTO.builder()
                .username(enrolmentQueryResult.getUser().getUsername())
                .name(enrolmentQueryResult.getUser().getName())
                .course(enrolmentQueryResult.getCourse())
                .build();

        return new ResponseEntity<>(courseEnrolmentDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> enrolments(Principal principal) {
        List<Course> courses = courseEnrolmentService.getAllEnrolledCoursesByUsername(principal.getName());

        List<CourseDTO> response = courses.stream().map(course -> CourseDTO.builder()
                .identifier(course.getIdentifier())
                .title(course.getTitle())
                .teacher(course.getTeacher())
                .lessons(lessonService.getAllLessonsByCourse(course.getIdentifier()))
                .isEnrolled(true)
                .build()).collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
