package org.server.repository;

import org.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getById(Long id);

    List<User> findAllByFriendsContains(User user);

    List<User> findAllByFriendsNotContains(User user);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);
}
