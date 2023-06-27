package de.zoolanderbackend.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepo extends JpaRepository<Post, UUID> {
}
