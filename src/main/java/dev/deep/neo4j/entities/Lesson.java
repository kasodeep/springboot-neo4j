package dev.deep.neo4j.entities;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Data
@Node
public class Lesson {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String identifier;
}
