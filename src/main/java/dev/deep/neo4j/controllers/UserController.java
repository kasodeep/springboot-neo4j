package dev.deep.neo4j.controllers;

import dev.deep.neo4j.entities.User;
import dev.deep.neo4j.objects.UserDTO;
import dev.deep.neo4j.requests.CreateUserRequest;
import dev.deep.neo4j.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public String loggedInUser(Principal principal) {
        return principal.getName();
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> signup(@RequestBody CreateUserRequest request) {
        User user = userService.createUser(request);

        UserDTO userDTO = UserDTO.builder().name(user.getName()).username(user.getUsername()).roles(user.getRoles()).build();
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }
}

/* Principal contains which user is authenticated right now.
 * Security -> UserDetailsService -> User Found -> Check Password.
 */