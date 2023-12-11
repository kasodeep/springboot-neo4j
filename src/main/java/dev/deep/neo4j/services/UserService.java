package dev.deep.neo4j.services;

import dev.deep.neo4j.entities.User;
import dev.deep.neo4j.repositories.UserRepository;
import dev.deep.neo4j.requests.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(CreateUserRequest request) {
        // TODO: Make sure that this doesn't exists.
        User user = User.builder()
                .name(request.getName())
                .username(request.getUsername())
                .roles(request.getRoles())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);
        return user;
    }
}