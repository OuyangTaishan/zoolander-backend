package de.zoolanderbackend.comment;

import de.zoolanderbackend.post.Post;
import de.zoolanderbackend.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    // fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String text;

    @ManyToOne
    User user;

    @ManyToOne
    Post post;

}
