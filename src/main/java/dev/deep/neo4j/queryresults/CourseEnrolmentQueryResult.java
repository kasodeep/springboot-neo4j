package dev.deep.neo4j.queryresults;

import dev.deep.neo4j.entities.Course;
import dev.deep.neo4j.entities.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseEnrolmentQueryResult {

    private User user;

    private Course course;
}
