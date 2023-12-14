package com.sytoss.ecommerce.platform.userservice.controller;

import com.sytoss.ecommerce.platform.userservice.model.User;
import com.sytoss.ecommerce.platform.userservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody User user) {
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{uid}")
                .buildAndExpand(savedUser.getUid())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public List<User> getUsers(@RequestParam(required = false) String login) {
        return StringUtils.isEmpty(login) ? userRepository.findAll() : userRepository.findAllByLogin(login);
    }

    @GetMapping("/{uid}")
    public User getUserByUid(@PathVariable UUID uid) {
        return userRepository.findById(uid).orElse(null);
    }
}
