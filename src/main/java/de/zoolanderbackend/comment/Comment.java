package de.zoolanderbackend.comment;

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
    // User user; "basic attribute should not be persistence entity"

}
