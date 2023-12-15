package com.sytoss.ecommerce.platform.userservice.service.impl;

import com.sytoss.ecommerce.platform.userservice.model.User;
import com.sytoss.ecommerce.platform.userservice.repository.UserRepository;
import com.sytoss.ecommerce.platform.userservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class DefaultUserService implements UserService {
    private final UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getUsersByLogin(String login) {
        return userRepository.findAllByLogin(login);
    }

    @Override
    public Optional<User> getUserByUid(UUID uid) {
        return userRepository.findById(uid);
    }
}
