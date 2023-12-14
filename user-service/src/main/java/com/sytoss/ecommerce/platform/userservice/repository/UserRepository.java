package com.sytoss.ecommerce.platform.userservice.repository;

import com.sytoss.ecommerce.platform.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findAllByLogin(String login);
}
