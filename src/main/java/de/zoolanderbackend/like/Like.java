package de.zoolanderbackend.like;

import de.zoolanderbackend.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name="_LIKE") // "like" is reserved in h2 database
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Like {

    // remember that while every comment is different, likes are always the same
    // so do they even need a unique identifier?
    // perhaps they do for association with posts (and comments) and the sameness is expressed in the boolean (vis-a-vis the String text variable in comments)
    @Id
    private UUID likeID;
    private boolean melikey;

    @ManyToOne
    private User user;
}
