package de.zoolanderbackend.post;

import de.zoolanderbackend.comment.Comment;
import de.zoolanderbackend.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @NonNull
    private String postLink;

    @ManyToOne
    User user;

    @OneToMany(mappedBy = "post", cascade = {CascadeType.ALL}) // delete all comments with their post
    private List<Comment> comments;

}
