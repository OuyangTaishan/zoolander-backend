package de.zoolanderbackend.user;

import de.zoolanderbackend.post.Post;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="_USER") // "user" is reserved in h2 database
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    // fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    private int age;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;
}
