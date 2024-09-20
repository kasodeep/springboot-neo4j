package dev.deep.neo4j.repositories;

import dev.deep.neo4j.entities.Course;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends Neo4jRepository<Course, Long> {

    Optional<Course> findCourseByIdentifier(String identifier);

    @Query("MATCH (:User {username: $username})-[:ENROLLED_IN]->(courses:Course) " +
            "RETURN courses")
    List<Course> findAllEnrolledCoursesByUsername(String username);
}
