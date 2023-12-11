package dev.deep.neo4j.objects;

import dev.deep.neo4j.entities.Lesson;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CourseDTO {

    private String identifier;

    private String title;

    private String teacher;

    private Boolean isEnrolled;

    private List<Lesson> lessons;
}
