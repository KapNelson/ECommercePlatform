package com.sytoss.ecommerce.platform.userservice.service;

import com.sytoss.ecommerce.platform.userservice.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The service interface for managing users.
 */
public interface UserService {

    /**
     * Saves a new user or updates an existing one.
     *
     * @param user The user to be saved or updated.
     * @return The saved or updated user.
     */
    User saveUser(User user);

    /**
     * Retrieves a list of all users.
     *
     * @return The list of all users.
     */
    List<User> getUsers();

    /**
     * Retrieves a list of users with the specified login.
     *
     * @param login The login to filter users by.
     * @return The list of users with the specified login.
     */
    List<User> getUsersByLogin(String login);

    /**
     * Retrieves a user by its unique identifier (UID).
     *
     * @param uid The unique identifier of the user.
     * @return An {@link Optional} containing the user with the specified UID, or empty if not found.
     */
    Optional<User> getUserByUid(UUID uid);
}
