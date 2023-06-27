package de.zoolanderbackend.comment;

import de.zoolanderbackend.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    private UUID commentID;
    private String text;

    @ManyToOne
    private User user;

}
