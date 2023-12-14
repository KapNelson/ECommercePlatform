package com.sytoss.ecommerce.platform.userservice.controller;

import com.sytoss.ecommerce.platform.userservice.model.User;
import com.sytoss.ecommerce.platform.userservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
public class UserController {
    private UserRepository userRepository;
    @PostMapping("/users")
    public void createUser(@RequestBody User user) {
        userRepository.save(user);
    }

    @GetMapping("/users")
    public List<User> getUsers(@RequestParam(required = false) String login) {
        return StringUtils.isEmpty(login) ? userRepository.findAll() : userRepository.findAllByLogin(login);
    }

    @GetMapping("/users/{uid}")
    public User getUserByUid(@PathVariable UUID uid) {
        return userRepository.findById(uid).orElse(null);
    }
}
