package dev.deep.neo4j.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserRequest {

    private String name;

    private String username;

    private String password;

    private String roles;
}
