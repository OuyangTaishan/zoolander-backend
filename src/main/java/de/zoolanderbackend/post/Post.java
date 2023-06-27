package de.zoolanderbackend.post;

import de.zoolanderbackend.comment.Comment;
import de.zoolanderbackend.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    private UUID postID;
    private String link;

    @ManyToOne
    User user;

    @OneToMany
    private List<Comment> comments;

}
