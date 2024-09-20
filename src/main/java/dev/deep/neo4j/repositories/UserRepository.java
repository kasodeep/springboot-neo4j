package dev.deep.neo4j.repositories;

import dev.deep.neo4j.entities.User;
import dev.deep.neo4j.queryresults.CourseEnrolmentQueryResult;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Optional;

public interface UserRepository extends Neo4jRepository<User, Long> {

    Optional<User> findUserByUsername(String username);

    @Query("MATCH (user:User { username:$username }), (course:Course { identifier: $identifier }) " +
            "RETURN EXISTS((user)-[:ENROLLED_IN]->(course))")
    Boolean findEnrolmentStatus(String username, String identifier);

    @Query("MATCH (user:User), (course:Course) WHERE user.username = $username AND course.identifier = $identifier " +
            "MERGE (user)-[:ENROLLED_IN]->(course) " +
            "RETURN user, course")
    CourseEnrolmentQueryResult createEnrolmentRelationship(String username, String identifier);
}
