package de.zoolanderbackend.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name="_USER") // "user" is reserved in h2 database
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private UUID userID;
    private String name;
    private String password;

}
