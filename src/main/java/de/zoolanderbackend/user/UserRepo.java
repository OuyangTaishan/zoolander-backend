package de.zoolanderbackend.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {
    Optional<User> findByUsernameAndPassword(String username, String password);
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
}
