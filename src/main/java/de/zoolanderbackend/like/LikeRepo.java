package de.zoolanderbackend.like;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LikeRepo extends JpaRepository<Like, UUID> {
}
