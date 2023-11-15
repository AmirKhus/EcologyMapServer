package com.example.vkr_server.service;

import com.example.vkr_server.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User getById(Long id);

    void save(User user);

    void delete(long id);

    List<User> getAll();

    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);

    Optional<User> findByUsername(String Username);

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);

    List<User> getFriends(User user);

    List<User> getNotFriends(User user);
}
