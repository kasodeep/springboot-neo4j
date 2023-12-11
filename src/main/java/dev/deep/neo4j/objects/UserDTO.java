package dev.deep.neo4j.objects;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {

    private String name;

    private String username;

    private String roles;
}
