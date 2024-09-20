package dev.deep.neo4j.objects;

import dev.deep.neo4j.entities.Course;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseEnrolmentDTO {

    private String username;

    private String name;

    private Course course;
}
